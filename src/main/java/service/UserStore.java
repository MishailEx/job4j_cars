package service;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.function.Function;

public class UserStore implements UserRepository {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final UserRepository INST = new UserStore();
    }

    public static UserRepository instOf() {
        return Lazy.INST;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public User addUser(User user) {
        int id = (Integer) this.tx(session -> session.save(user));
        user.setId(id);
        return user;
    }

    @Override
    public User updateUser(User user, int id) {
        this.tx(session -> session.createQuery("update User u set u.name = :name, u.password = :pass, u.numAnnouncement = :num where u.id = :id")
                .setParameter("name", user.getName()))
                .setParameter("pass", user.getPassword())
                .setParameter("num", user.getNumAnnouncement())
                .setParameter("id", id)
                .executeUpdate();
        user.setId(id);
        return user;
    }

    @Override
    public User findByName(String name) {
        return (User) this.tx(session -> session.createQuery("from User u where u.name = :name")
                .setParameter("name", name).uniqueResult());
    }

    public static User getUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        User userUpdate = instOf().findByName(user.getName());
        session.setAttribute("user", userUpdate);
        return userUpdate;
    }

    public static String getNameAnn(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (String) session.getAttribute("nameAnnouncement");
    }

    @Override
    public void incNumAnn(HttpServletRequest req) {
        User user = getUser(req);
        int num = user.getNumAnnouncement() + 1;
        this.tx(session -> session.createQuery("update User set numAnnouncement = :num where id = :id ")
                .setParameter("num", num)
                .setParameter("id", user.getId())
                .executeUpdate());
        user.setNumAnnouncement(user.getNumAnnouncement() + 1);
        instOf().updateUser(user, user.getId());
    }
}

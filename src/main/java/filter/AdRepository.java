package filter;

import model.Announcement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.function.Function;

public class AdRepository {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();


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


    public List<Announcement> lastDayAnnouncements() {
        return this.tx(session -> session.createQuery("select distinct an from Announcement an "
                + "join fetch an.car c "
                + "join fetch c.images "
                + "where an.created > CURRENT_DATE").list());
    }

    public List<Announcement> hasImageAnnouncements() {
        return this.tx(session -> session.createQuery("select distinct an from Announcement an "
                + "join fetch an.car c "
                + "join fetch c.images im "
                + "where im is not null ").list());
    }

    public List<Announcement> MarkAnnouncements(String s) {
        return this.tx(session -> session.createQuery("select distinct an from Announcement an "
                + "join fetch an.car c "
                + "join fetch c.images "
                + "where c.mark.name = :marka").setParameter("marka", s).list());
    }
}


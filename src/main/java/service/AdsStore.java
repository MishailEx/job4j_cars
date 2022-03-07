package service;

import model.Announcement;
import model.Car;
import model.Image;
import model.Year;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AdsStore implements AdsRepository {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final AdsRepository INST = new AdsStore();
    }

    public static AdsRepository instOf() {
        return AdsStore.Lazy.INST;
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
    public Announcement addAnn(Announcement an) {
        int id = (Integer) this.tx(session -> session.save(an));
        an.setId(id);
        return an;
    }

    @Override
    public Image addImg(Image image) {
        int id = (Integer) this.tx(session -> session.save(image));
        image.setId(id);
        return image;
    }

    @Override
    public Announcement findAnnById(int id) {
        return (Announcement) this.tx(session -> session.createQuery("from Announcement a where a.id = :id")
                .setParameter("id", id)
                .uniqueResult());
    }

    @Override
    public List<Announcement> filterFindCar(Car car, Year yearSt, Year yearEnd) {
        List<Announcement> cars = new ArrayList<>();
        if (car.getMark() != null) {
            List<Announcement> list = this.tx(session -> session.createQuery("from Announcement a join"
                    + " fetch a.car c where c.mark = :mark and a.sold = true").setParameter("mark", car.getMark()).list());
            cars = checkSetList(cars, list);
        }
        if (car.getModel() != null) {
            List<Announcement> list = this.tx(session -> session.createQuery("from Announcement a join"
                    + " fetch a.car c where c.model = :model and a.sold = true").setParameter("model", car.getModel()).list());
            cars = checkSetList(cars, list);
        }
        if (car.getBody() != null) {
            List<Announcement> list = this.tx(session -> session.createQuery("from Announcement a join"
                    + " fetch a.car c where c.body = :body and a.sold = true").setParameter("body", car.getBody()).list());
            cars = checkSetList(cars, list);
        }

        if (car.getEngine() != null) {
            List<Announcement> list = this.tx(session -> session.createQuery("from Announcement a join"
                    + " fetch a.car c where c.engine = :engine and a.sold = true").setParameter("engine", car.getEngine()).list());
            cars = checkSetList(cars, list);
        }
        if (yearSt != null) {
            List<Announcement> list = this.tx(session -> session.createQuery("from Announcement a join"
                    + " fetch a.car c where c.year.name >= :yearSt and a.sold = true").setParameter("yearSt", yearSt.getName()).list());
            cars = checkSetList(cars, list);

        }
        if (yearEnd != null) {
            List<Announcement> list = this.tx(session -> session.createQuery("from Announcement a join"
                    + " fetch a.car c where c.year.name <= :yearEnd and a.sold = true").setParameter("yearEnd", yearEnd.getName()).list());
            cars = checkSetList(cars, list);
        }
        return cars;
    }

    @Override
    public List<Announcement> findAllCar() {
        return this.tx(session -> session.createQuery("from Announcement a where a.sold = true").list());
    }

    private List<Announcement> checkSetList(List<Announcement> rslList, List<Announcement> filterList) {
        if (rslList.isEmpty()) {
            rslList.addAll(filterList);
        } else {
            rslList.retainAll(filterList);
        }
        return rslList;
    }

}

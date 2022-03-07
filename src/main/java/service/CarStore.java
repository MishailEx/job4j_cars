package service;

import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.*;
import java.util.function.Function;

public class CarStore implements CarRepository {


    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final CarRepository INST = new CarStore();
    }

    public static CarRepository instOf() {
        return CarStore.Lazy.INST;
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
    public Car addCar(Car car) {
        int id = (Integer) this.tx(session -> session.save(car));
        car.setId(id);
        return car;
    }

    @Override
    public List<Model> findModelOnMark(Mark mark) {
        return this.tx(session -> session.createQuery("from Model m where m.mark.id = :id ")
                .setParameter("id", mark.getId()).list());
    }

    @Override
    public Map<String, List<Object>> loadAllSelect() {
        Map<String, List<Object>> all = new HashMap<>();
        all.put("mark", this.tx(session -> session.createQuery("from Mark ").list()));
        all.put("body", this.tx(session -> session.createQuery("from Body").list()));
        all.put("engine", this.tx(session -> session.createQuery("from Engine").list()));
        all.put("model", this.tx(session -> session.createQuery("from Model").list()));
        all.put("transmission", this.tx(session -> session.createQuery("from Transmission").list()));
        all.put("year", this.tx(session -> session.createQuery("from Year").list()));
        return all;
    }

    @Override
    public Car findCar(Car car) {
        Car car1 = (Car) this.tx(session -> session.createQuery("from Car"
                + " where mark = :mark and body = :body and engine = :engine and model = :model")
                .setParameter("mark", car.getMark())
                .setParameter("body", car.getBody())
                .setParameter("engine", car.getEngine())
                .setParameter("model", car.getModel())
                .uniqueResult());
        if (car1 == null) {
            instOf().addCar(car);
            car1 = car;
        }
        return car1;
    }
}

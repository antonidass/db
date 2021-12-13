package ormwork;

import jakarta.persistence.PersistenceContext;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();
    @PersistenceContext

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure(new File("src/main/resources/hibernate.cfg.xml")).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("initial session factory creation failed " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() { return  sessionFactory; }

    public static void close() {
        getSessionFactory().close();
    }
}

package com.example.movie.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Class that configures the session factory used by hibernate
public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;

    // Static block to instantiate the session factory
    static {
        try {
            SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}

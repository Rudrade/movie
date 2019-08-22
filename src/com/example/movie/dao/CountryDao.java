package com.example.movie.dao;

import com.example.movie.model.Country;
import com.example.movie.util.HibernateUtil;
import com.example.movie.util.exceptions.NotFound;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;

public class CountryDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Transactional
    public Country create(Country country) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(country);
        session.getTransaction().commit();
        session.close();

        return country;
    }

    @Transactional
    public Country getById(int id) throws NotFound {
        Session session = sessionFactory.openSession();
        Country country = session.get(Country.class, id);
        session.close();

        if (country == null) {
            throw new NotFound();
        } else {
            return country;
        }
    }

    @Transactional
    public void update(Country country) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(country);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void delete(Country country) {
        Session session = sessionFactory.openSession();
        session.delete(country);
        session.close();
    }
}

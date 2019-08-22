package com.example.movie.dao;

import com.example.movie.model.Person;
import com.example.movie.util.HibernateUtil;
import com.example.movie.util.exceptions.NotFound;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;

public class PersonDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Transactional
    public Person create(Person person) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
        session.close();

        return person;
    }

    @Transactional
    public Person getById(int id) throws NotFound {
        Session session = sessionFactory.openSession();
        Person person = session.get(Person.class, id);
        session.close();

        if (person == null) {
            throw new NotFound();
        } else {
            return person;
        }
    }

    @Transactional
    public void update(Person person) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(person);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void delete(Person person) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(person);
        session.getTransaction().commit();
        session.close();
    }
}

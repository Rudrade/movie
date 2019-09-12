package com.example.movie.dao;

import com.example.movie.model.Person;
import com.example.movie.util.HibernateUtil;
import com.example.movie.util.exceptions.NotFound;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.List;

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
    public List<Person> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Person> personList = session.createQuery("FROM Person ORDER BY id").list();
        session.close();
        return personList;
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
    public void deleteById(int id) throws NotFound {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Person person = session.get(Person.class, id);

        if (person == null) {
            throw new NotFound();
        }

        session.delete(person);
        session.getTransaction().commit();
        session.close();
    }

    // Method to get a person first name given an certain word
    @Transactional
    public List<Person> search(String keyword) throws NotFound {
        String sql = "FROM Person p WHERE p.firstName LIKE '%" + keyword + "%' OR p.lastName LIKE '%" + keyword + "%'";

        Session session = sessionFactory.openSession();
        List<Person> personList = session.createQuery(sql).list();
        session.close();

        if (personList.isEmpty()) {
            throw new NotFound();
        }

        return personList;
    }
}

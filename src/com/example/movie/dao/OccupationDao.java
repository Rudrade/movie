package com.example.movie.dao;

import com.example.movie.model.Occupation;
import com.example.movie.util.HibernateUtil;
import com.example.movie.util.exceptions.NotFound;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.List;

public class OccupationDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Transactional
    public List<Occupation> getAll() {
        Session session = sessionFactory.openSession();
        List<Occupation> occupationList = session.createQuery("FROM Occupation ORDER BY id").list();
        session.close();
        return occupationList;
    }

    @Transactional
    public Occupation create(Occupation occupation) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(occupation);
        session.getTransaction().commit();
        session.close();
        return occupation;
    }

    @Transactional
    public Occupation getById(int id) throws NotFound {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Occupation occupation = session.get(Occupation.class, id);
        session.getTransaction().commit();
        session.close();

        if (occupation == null) {
            throw new NotFound();
        } else {
            return occupation;
        }
    }

    @Transactional
    public void update(Occupation occupation) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(occupation);
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public void deleteById(int id) throws NotFound {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Occupation occupation = session.get(Occupation.class, id);

        if (occupation == null) {
            throw new NotFound();
        }

        session.delete(occupation);
        session.getTransaction().commit();
        session.close();
    }
}

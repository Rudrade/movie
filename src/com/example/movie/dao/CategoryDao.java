package com.example.movie.dao;

import com.example.movie.model.Category;
import com.example.movie.util.HibernateUtil;
import com.example.movie.util.exceptions.NotFound;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.List;

public class CategoryDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Transactional
    public Category getById(int id) throws NotFound {
        Session session = sessionFactory.openSession();
        Category category = session.get(Category.class, id);
        session.close();

        if (category == null) {
            throw new NotFound();
        }

        return category;
    }


    @Transactional
    public List<Category> getAll() {
        Session session = sessionFactory.openSession();
        List<Category> categoryList = session.createQuery("FROM Category ORDER BY id").list();
        session.close();
        return categoryList;
    }

    @Transactional
    public Category create(Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
        session.close();
        return category;
    }

    @Transactional
    public Category update(Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(category);
        session.getTransaction().commit();
        session.close();
        return category;
    }

    @Transactional
    public void deleteById(int id) throws NotFound {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Category category = session.get(Category.class, id);

        if (category == null) {
            session.close();
            throw new NotFound();
        }

        session.delete(category);
        session.getTransaction().commit();
        session.close();
    }
}

package com.example.movie.dao;

import com.example.movie.model.User;
import com.example.movie.util.HibernateUtil;
import com.example.movie.util.exceptions.NotFound;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

public class UserDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Transactional
    public void login(String username, String password) throws NotFound {
        String sql = "FROM User u WHERE u.username LIKE '" + username + "' AND u.password LIKE '" + password + "'";

        Session session = sessionFactory.openSession();
        List<User> userList = session.createQuery(sql, User.class).list();

        if (userList.isEmpty()) {
            throw new NotFound();
        }
    }

    @Transactional
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        List<User> userList = session.createQuery("FROM User").list();
        session.close();
        return userList;
    }

    @Transactional
    public User getByUsername(String username) throws NotFound {
        User user;
        String sql = "FROM User u WHERE u.username LIKE '" + username + "'";

        Session session = sessionFactory.openSession();
        try {
           user = (User) session.createQuery(sql).getSingleResult();
        } catch (NoResultException e) {
            session.close();
            throw new NotFound();
        }
        session.close();

        return user;
    }
}

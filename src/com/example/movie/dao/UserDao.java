package com.example.movie.dao;

import com.example.movie.model.User;
import com.example.movie.util.HibernateUtil;
import com.example.movie.util.exceptions.NotFound;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void login(String username, String password) throws NotFound {
        String sql = "FROM User u WHERE u.username LIKE '" + username + "' AND u.password LIKE '" + password + "'";

        Session session = sessionFactory.openSession();
        List<User> userList = session.createQuery(sql, User.class).list();

        if (userList.isEmpty()) {
            throw new NotFound();
        }
    }
}

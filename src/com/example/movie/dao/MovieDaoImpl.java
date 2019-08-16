package com.example.movie.dao;

import com.example.movie.model.Movie;
import com.example.movie.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MovieDaoImpl implements MovieDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void create(Movie movie) {

    }

    @Override
    public List<Movie> getAll() {
        Session session = sessionFactory.openSession();
        List<Movie> movieList = session.createQuery("from Movie").list();
        session.close();
        return movieList;
    }
}

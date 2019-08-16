package com.example.movie.dao;

import com.example.movie.model.Movie;
import com.example.movie.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.List;

public class MovieDaoImpl implements MovieDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    // Method to insert a movie into the database
    @Override
    @Transactional
    public Movie insertOrUpdate(Movie movie) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(movie);
        session.getTransaction().commit();
        session.close();
        return movie;
    }

    // Method to delete a movie from the database
    @Override
    @Transactional
    public void delete(Movie movie) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(movie);
        session.getTransaction().commit();
        session.close();
    }

    // Method that return the movie given an id
    @Override
    @Transactional
    public Movie getById(int id) {
        Session session = sessionFactory.openSession();
        Movie movie = session.get(Movie.class, id);
        session.close();
        return movie;
    }

    // Method that returns all movies in the DB
    @Override
    @Transactional
    public List<Movie> getAll() {
        Session session = sessionFactory.openSession();
        List<Movie> movieList = session.createQuery("from Movie").list();
        session.close();
        return movieList;
    }
}

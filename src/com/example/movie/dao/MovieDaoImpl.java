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
    public Movie insert(Movie movie) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(movie);
        session.getTransaction().commit();
        session.close();
        return movie;
    }

    @Override
    @Transactional
    public Movie update(Movie movie) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(movie);
        session.getTransaction().commit();
        return movie;
    }

    // Method to delete a movie from the database
    @Override
    @Transactional
    public void deleteById(int id) throws MovieNotFound {
        Movie movie = getById(id);
        if (movie != null) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(movie);
            session.getTransaction().commit();
            session.close();
        } else {
            throw new MovieNotFound();
        }
    }

    // Method that return the movie given an id
    @Override
    @Transactional
    public Movie getById(int id) throws MovieNotFound {
        Session session = sessionFactory.openSession();
        Movie movie = session.get(Movie.class, id);
        session.close();
        if (movie == null) {
            throw new MovieNotFound();
        } else {
            return movie;
        }
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

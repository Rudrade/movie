package com.example.movie.dao;

import com.example.movie.model.Movie;
import com.example.movie.util.HibernateUtil;
import com.example.movie.util.exceptions.NotFound;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

public class MovieDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Transactional
    public Movie insert(Movie movie) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(movie);
        session.getTransaction().commit();
        session.close();
        return movie;
    }

    @Transactional
    public Movie update(Movie movie) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(movie);
        session.getTransaction().commit();
        return movie;
    }

    @Transactional
    public void deleteById(int id) throws NotFound {
        Movie movie = getById(id);
        if (movie != null) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(movie);
            session.getTransaction().commit();
            session.close();
        } else {
            throw new NotFound();
        }
    }

    @Transactional
    public Movie getById(int id) throws NotFound {
        Session session = sessionFactory.openSession();
        Movie movie = session.get(Movie.class, id);
        session.close();
        if (movie == null) {
            throw new NotFound();
        } else {
            return movie;
        }
    }

    @Transactional
    public List<Movie> getAll() {
        Session session = sessionFactory.openSession();
        List<Movie> movieList = session.createQuery("FROM Movie").list();
        session.close();
        return movieList;
    }

    // Method to get a movie title given an certain word
    @Transactional
    public List<Movie> getByKeyword(String keyword) throws NotFound {
        String sql = "FROM Movie m WHERE m.title LIKE '%" + keyword + "%'";

        Session session = sessionFactory.openSession();
        Query query = session.createQuery(sql);
        List<Movie> movieList = query.list();
        session.close();

        if (movieList.isEmpty()) {
            throw new NotFound();
        }

        return movieList;
    }
}

package com.example.movie.dao;

import com.example.movie.model.Movie;

import java.util.List;

public interface MovieDao {
    Movie insertOrUpdate(Movie movie);
    void delete(Movie movie);
    Movie getById(int id);
    List<Movie> getAll();
}

package com.example.movie.dao;

import com.example.movie.model.Movie;

import java.util.List;

public interface MovieDao {
    Movie insertOrUpdate(Movie movie);
    void deleteById(int id) throws MovieNotFound;
    Movie getById(int id) throws MovieNotFound;
    List<Movie> getAll();
}

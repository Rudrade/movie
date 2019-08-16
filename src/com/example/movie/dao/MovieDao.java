package com.example.movie.dao;

import com.example.movie.model.Movie;

import java.util.List;

public interface MovieDao {
    void create(Movie movie);
    List<Movie> getAll();
}

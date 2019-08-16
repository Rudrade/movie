package com.example.movie.resources;

import com.example.movie.dao.MovieDao;
import com.example.movie.dao.MovieDaoImpl;
import com.example.movie.model.Movie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("movies")
public class MovieResource {
    private MovieDao dao = new MovieDaoImpl();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Movie> getAll() {
        return dao.getAll();
    }
}

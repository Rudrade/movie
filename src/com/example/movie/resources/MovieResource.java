package com.example.movie.resources;

import com.example.movie.dao.MovieDao;
import com.example.movie.dao.MovieDaoImpl;
import com.example.movie.model.Movie;

import javax.ws.rs.*;
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

    @POST
    @Path("movie")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Movie insert(Movie movie) {
        return dao.insertOrUpdate(movie);
    }
}

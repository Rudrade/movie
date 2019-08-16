package com.example.movie.resources;

import com.example.movie.dao.MovieDao;
import com.example.movie.dao.MovieDaoImpl;
import com.example.movie.dao.MovieNotFound;
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
    @Path("add")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Movie insert(Movie movie) {
        return dao.insertOrUpdate(movie);
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteById(@PathParam("id") int id) {
        try {
            dao.deleteById(id);
            return "Filme apagado com sucesso.";
        } catch (MovieNotFound movieNotFound) {
            return "Não existem filmes com o id inserido.";
        }
    }
}

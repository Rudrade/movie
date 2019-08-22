package com.example.movie.resources;

import com.example.movie.dao.MovieDao;
import com.example.movie.model.Movie;
import com.example.movie.util.exceptions.NotFound;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("movies")
public class MovieResource {
    private MovieDao dao = new MovieDao();

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
        return dao.insert(movie);
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteById(@PathParam("id") int id) {
        try {
            dao.deleteById(id);
            return "Filme apagado com sucesso.";
        } catch (NotFound movieNotFound) {
            movieNotFound.printStackTrace();
            return null;
        }
    }

    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Movie update(Movie movie) {
        return dao.update(movie);
    }

    @GET
    @Path("movie/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getById(@PathParam("id") int id)  {
        try {
            Movie movie = dao.getById(id);
            return Response.status(Response.Status.OK)
                    .entity(movie)
                    .build();
        } catch (NotFound e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @Path("search/{keyword}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getByKeyword(@PathParam("keyword") String keyword) {
        try {
            List<Movie> movieList = dao.getByKeyword(keyword);
            GenericEntity entity = new GenericEntity<List<Movie>>(movieList){};
            return Response.status(Response.Status.OK)
                    .entity(entity)
                    .build();
        } catch (NotFound e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }
}

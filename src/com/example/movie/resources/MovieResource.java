package com.example.movie.resources;

import com.example.movie.dao.MovieDao;
import com.example.movie.model.Movie;
import com.example.movie.util.exceptions.NotFound;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("movie")
public class MovieResource {
    private static final Logger LOGGER = Logger.getLogger("");

    private MovieDao dao = new MovieDao();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getAll() {
        List<Movie> movieList = dao.getAll();
        GenericEntity entity = new GenericEntity<List<Movie>>(movieList){};
        return Response.status(200).entity(entity).build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response create(Movie movie) {
        LOGGER.info("MOVIE --> " + movie.toString());
        Movie m = dao.insert(movie);
        return Response.status(201).entity(m).build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteById(@PathParam("id") int id) {
        try {
            dao.deleteById(id);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }

    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response update(Movie movie) {
        try {
            dao.update(movie);
            return Response.status(200).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getById(@PathParam("id") int id)  {
        try {
            Movie movie = dao.getById(id);
            return Response.status(200).entity(movie).build();
        } catch (NotFound e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_XML)
    public Response getByKeyword(@QueryParam("keyword") String keyword) {
        try {
            List<Movie> movieList = dao.getByKeyword(keyword.trim());
            GenericEntity entity = new GenericEntity<List<Movie>>(movieList){};
            return Response.status(Response.Status.OK).entity(entity).build();
        } catch (NotFound e) {
            return Response.status(404).build();
        }
    }
}

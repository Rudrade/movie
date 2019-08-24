package com.example.movie.resources;

import com.example.movie.dao.CategoryDao;
import com.example.movie.model.Category;
import com.example.movie.util.exceptions.NotFound;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("category")
public class CategoryResource {
    private CategoryDao dao = new CategoryDao();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getAll() {
        List<Category> categoryList = dao.getAll();
        GenericEntity entity = new GenericEntity<List<Category>>(categoryList){};
        return Response.status(200).entity(entity).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getById(@PathParam("id") int id) {
        try {
            Category category = dao.getById(id);
            return Response.status(200).entity(category).build();
        } catch (NotFound e) {
            return Response.status(404).build();
        }
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response create(Category category) {
        Category c = dao.create(category);
        return Response.status(201).entity(c).build();
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response update(Category category) {
        try {
            Category c = dao.update(category);
            return Response.status(200).entity(c).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteById(@PathParam("id") int id) {
        try {
            dao.deleteById(id);
            return Response.status(200).build();
        } catch (NotFound e) {
            return Response.status(404).build();
        }
    }
}

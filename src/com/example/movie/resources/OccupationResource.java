package com.example.movie.resources;

import com.example.movie.dao.OccupationDao;
import com.example.movie.model.Occupation;
import com.example.movie.util.exceptions.NotFound;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("occupation")
public class OccupationResource {
    private OccupationDao dao = new OccupationDao();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getAll() {
        List<Occupation> occupationList = dao.getAll();
        GenericEntity entity = new GenericEntity<List<Occupation>>(occupationList){};
        return Response.status(200).entity(entity).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getById(@PathParam("id") int id) {
        try {
            Occupation occupation = dao.getById(id);
            return Response.status(200).entity(occupation).build();
        } catch (NotFound e) {
            return Response.status(404).build();
        }
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response create(Occupation occupation) {
        Occupation o = dao.create(occupation);
        return Response.status(201).entity(o).build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response update(Occupation occupation) {
        try {
            dao.update(occupation);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteById(@PathParam("id") int id) {
        try {
            dao.deleteById(id);
            return Response.status(200).build();
        } catch (NotFound e) {
            return Response.status(404).build();
        }
    }
}

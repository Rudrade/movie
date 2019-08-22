package com.example.movie.resources;

import com.example.movie.dao.RoleDao;
import com.example.movie.model.Occupation;
import com.example.movie.util.exceptions.NotFound;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("role")
public class RoleResource {
    private RoleDao dao = new RoleDao();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getAll() {
        List<Occupation> occupationList = dao.getAll();
        GenericEntity entity = new GenericEntity<List<Occupation>>(occupationList){};
        return Response
                .status(200)
                .entity(entity)
                .build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response create(Occupation occupation) {
        dao.create(occupation);
        return Response
                .status(201)
                .build();
    }

    @DELETE
    @Path("delete/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteById(@PathParam("id") int id) {
        try {
            dao.delete(dao.getById(id));
            return Response
                    .status(200)
                    .build();
        } catch (NotFound e) {
            return Response
                    .status(404)
                    .build();
        }
    }
}

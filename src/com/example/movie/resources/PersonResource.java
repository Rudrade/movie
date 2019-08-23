package com.example.movie.resources;

import com.example.movie.dao.PersonDao;
import com.example.movie.model.Person;
import com.example.movie.util.exceptions.NotFound;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("person")
public class PersonResource {
    private PersonDao dao = new PersonDao();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getAll() {
        List<Person> personList = dao.getAll();
        GenericEntity entity = new GenericEntity<List<Person>>(personList){};
        return Response
                .status(200)
                .entity(entity)
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getById(@PathParam("id") int id) {
        try {
            Person person = dao.getById(id);
            return Response
                    .status(200)
                    .entity(person)
                    .build();
        } catch (NotFound e) {
            return Response
                    .status(404)
                    .build();
        }
    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response create(Person person) {
        Person p = dao.create(person);
        return Response
                .status(201)
                .entity(p)
                .build();
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_XML)
    public Response update(Person person) {
        try {
            dao.update(person);
            return Response
                    .status(200)
                    .build();
        } catch (Exception e) {
            return Response
                    .status(404)
                    .build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteById(@PathParam("id") int id) {
        try {
            dao.deleteById(id);
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

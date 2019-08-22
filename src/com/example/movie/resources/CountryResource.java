package com.example.movie.resources;

import com.example.movie.dao.CountryDao;
import com.example.movie.model.Country;
import com.example.movie.util.exceptions.NotFound;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("country")
public class CountryResource {
    private CountryDao dao = new CountryDao();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getAll() {
        List<Country> countryList = dao.getAll();
        GenericEntity entity = new GenericEntity<List<Country>>(countryList){};
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
            Country country = dao.getById(id);
            return Response
                    .status(200)
                    .entity(country)
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
    public Response create(Country country) {
        Country c = dao.create(country);
        return Response
                .status(201)
                .entity(c)
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_XML)
    public Response update(Country country) {
        dao.update(country);

        return Response
                .status(200)
                .build();
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

package com.example.movie.resources;

import com.example.movie.dao.UserDao;
import com.example.movie.model.User;
import com.example.movie.util.exceptions.NotFound;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("user")
public class UserResource {
    private UserDao dao = new UserDao();

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAll() {
        List<User> userList = dao.getAll();
        GenericEntity entity = new GenericEntity<List<User>>(userList){};
        return Response.status(200).entity(entity).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getByUsername(@QueryParam("username") String username) {
        try {
            User user = dao.getByUsername(username);
            return Response.status(200).entity(user).build();
        } catch (NotFound e) {
            return Response.status(404).build();
        }
    }
}

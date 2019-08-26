package com.example.movie.resources;

import com.example.movie.dao.UserDao;
import com.example.movie.util.exceptions.NotFound;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserResource {
    private UserDao dao = new UserDao();

    @POST
    @Path("login")
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        try {
            dao.login(username, password);
            return Response.status(200).build();
        } catch (NotFound e) {
            return Response.status(404).build();
        }
    }
}

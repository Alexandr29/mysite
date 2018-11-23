package com.nixsolutions.webservice;

import com.nixsolutions.service.UserService;
import com.nixsolutions.service.impl.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/users")
public class RestControllerUser {

    @Autowired
    UserService userService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> getUsers() {
        return userService.findAll();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public void postUser(User user) {
        userService.create(user);
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public User getUser(@PathParam("id") long id) {
        return userService.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void putUser(@PathParam("id") long id, User user) {
        if (userService.findById(id) != null) {
            userService.update(user);
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam("id") long id, User user) {
        if (userService.findById(id) != null) {
            userService.remove(user);
        }
    }
}

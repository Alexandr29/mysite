package com.nixsolutions.webservice;

import com.nixsolutions.service.UserService;
import com.nixsolutions.service.impl.User;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/users")
public class RestControllerUser {
    private final UserService userService;
    RestControllerUser(UserService userService){
        this.userService = userService;
    }
    @GET
    @Path("/{id}")
    User getUser(@PathVariable Long id) {
        System.out.println(userService.findById(id).toString());
        return userService.findById(id);
    }
}

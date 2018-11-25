package com.nixsolutions.webservice;
import com.nixsolutions.service.UserService;

import com.nixsolutions.service.impl.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import javax.validation.ConstraintViolationException;
import java.util.List;

@WebService(endpointInterface = "com.nixsolutions.webservice.UserOperations",
        serviceName = "SoapWebService")
@Component
public class UserOperationsImpl implements UserOperations {

    @Autowired
    private UserService userService;

    @Override
    public User findById(Long id) {
        return userService.findById(id);
    }

    @Override
    public List<User> findAll() {
        System.out.println("i m here");
        return userService.findAll();
    }

    @Override
    public String createUser(User user) {
        try {
            userService.create(user);
        } catch (ConstraintViolationException e) {
            return "Error: user is not valid";
        }

        return "user created successful";
    }

    @Override
    public String updateUser(User user) {
        User userInDb = userService.findById(user.getId());
        try {
            userService.update(user);
        } catch (ConstraintViolationException e) {
            return "Error: user is not valid";
        }

        return "user updated successful";
    }

    @Override
    public String removeUser(User user) {
            userService.remove(user);


        return "user removed successful";
    }
}

package com.nixsolutions.webservice;



import com.nixsolutions.service.impl.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface UserOperations {

    @WebMethod
    @WebResult(name = "user")
    public User findById(@WebParam(name = "id") Long id);

    @WebMethod
    @WebResult(name = "listUsers")
    public List<User> findAll();

    @WebMethod
    public String createUser(@WebParam(name = "user") User user);

    @WebMethod
    public String updateUser(@WebParam(name = "user") User user);

    @WebMethod
    public String removeUser(@WebParam(name = "login") User user);
}

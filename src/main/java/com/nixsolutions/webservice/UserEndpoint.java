package com.nixsolutions.webservice;

import com.nixsolutions.service.UserService;
import com.nixsolutions.users.GetUserRequest;
import com.nixsolutions.users.GetUserResponse;
import com.nixsolutions.users.ObjectFactory;
import com.nixsolutions.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8080/soap/*";

    @Autowired UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetUserResponse processCourseDetailsRequest(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();

        User user = new User();
        user.setId(request.getId());
        user.setLogin("Adam");
        user.setPassword("E1234567");

        response.setUserDetails(user);

        return response;
    }


}

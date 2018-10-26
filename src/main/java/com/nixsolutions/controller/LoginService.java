package com.nixsolutions.controller;

public class LoginService {
    public boolean validateUser(String login, String password) {
        return login.equalsIgnoreCase("Admin") && password.equals("1234");
    }
}

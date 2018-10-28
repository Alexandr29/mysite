package com.nixsolutions.controller;

import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcUserDao;

public class LoginService {
    private JdbcUserDao jdbcUserDao = new JdbcUserDao();
    private User user;
    public boolean validateUser(String login, String password) {
         user = jdbcUserDao.findByLogin(login);
        System.out.println(user.getLogin() + " " + user.getPassword());
        return login.equalsIgnoreCase(user.getLogin()) && password.equals(user.getPassword());
    }
    public boolean isAdmin(String login){
        user = jdbcUserDao.findByLogin(login);
        return user.getRole_id().equals(1L);
    }
}

package com.nixsolutions.controller;

import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcUserDao;

import java.sql.SQLException;

public class LoginService {
    private JdbcUserDao jdbcUserDao = new JdbcUserDao();
    private User user;
    public boolean validateUser(String login, String password) {
        for (User user1:jdbcUserDao.findAll()) {
            if (user1.getLogin().equals(login)&&user1.getPassword().equals(password)){
                return true;
            }
        }return false;

        // return login.equalsIgnoreCase(user.getLogin()) && password.equals(user.getPassword());
    }
    public boolean isAdmin(String login){
        try {
            user = jdbcUserDao.findByLogin(login);
            return user.getRole_id().equals(1L);
        }catch (Exception e){
            return false;
        }

    }
}

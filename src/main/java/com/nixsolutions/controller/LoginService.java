package com.nixsolutions.controller;

import com.nixsolutions.service.hibernate.HibernateUserDao;
import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcUserDao;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public class LoginService {
    private HibernateUserDao hibernateUserDao = new HibernateUserDao();
    private User user;
    public boolean validateUser(String login, String password) {
        for (User user1:hibernateUserDao.findAll()) {
            if (user1.getLogin().equals(login)&&user1.getPassword().equals(password)){
                return true;
            }
        }return false;

        // return login.equalsIgnoreCase(user.getLogin()) && password.equals(user.getPassword());
    }
    public boolean isAdmin(String login){
        try {
            user = hibernateUserDao.findByLogin(login);
            return user.getRole_id()==1L;
        }catch (Exception e){
            return false;
        }

    }
}

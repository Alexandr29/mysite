package com.nixsolutions.controller;

import com.nixsolutions.service.UserService;
import com.nixsolutions.service.dao.UserDao;
import com.nixsolutions.service.hibernate.HibernateUserDao;
import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
@Service
public class LoginService {
    @Autowired
    private UserService userDao;

    //private HibernateUserDao hibernateUserDao = new HibernateUserDao();
    private User user;
    public boolean validateUser(String login, String password) {
        for (User user1:userDao.findAll()) {
            if (user1.getLogin().equals(login)&&user1.getPassword().equals(password)){
                return true;
            }
        }return false;

        // return login.equalsIgnoreCase(user.getLogin()) && password.equals(user.getPassword());
    }
    public boolean isAdmin(String login){
        try {
            user = userDao.findByLogin(login);
            return user.getRole_id()==1L;
        }catch (Exception e){
            return false;
        }

    }
}

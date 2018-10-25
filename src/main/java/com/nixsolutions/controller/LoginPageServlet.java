package com.nixsolutions.controller;
import com.nixsolutions.service.impl.Role;
import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcRoleDao;
import com.nixsolutions.service.jdbc.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class LoginPageServlet extends HttpServlet {
    private JdbcUserDao jdbcUserDao = new JdbcUserDao();
    private JdbcRoleDao jdbcRoleDao = new JdbcRoleDao();
    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = jdbcUserDao.findAll();
        System.out.println("I am in doPost");
        User user;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        user = jdbcUserDao.findByLogin(login);
        //createUser();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        req.setAttribute("users",users);
        req.setAttribute("firstName",firstName);
        req.setAttribute("lastName",lastName);
        if (user.getPassword().equals(password) && user.getRole_id()==1L){
            req.getRequestDispatcher("admin.jsp").forward(req,resp);
        }else {
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }


    }
    private void createUser(){
        User adminUser = new User("User", "1234", "alexru", "Alex", "Last",
                Date.valueOf("1997-04-29"), 2L);
        jdbcUserDao.create(adminUser);
    }
}

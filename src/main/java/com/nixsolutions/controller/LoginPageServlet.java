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
import java.util.ArrayList;
import java.util.List;

public class LoginPageServlet extends HttpServlet {
    private JdbcUserDao jdbcUserDao = new JdbcUserDao();
    private JdbcRoleDao jdbcRoleDao = new JdbcRoleDao();
    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("I an in doGet");
        //req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = jdbcUserDao.findAll();
        System.out.println("I an in doPost");
        User user;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        createAdmin();

        req.setAttribute("users",users);
        user = jdbcUserDao.findByLogin(login);
        users.add(user);
        if (user.getPassword().equals(password)){

            req.getRequestDispatcher("admin.jsp").forward(req,resp);
        }else {
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }
    }

    private void createAdmin(){
        Role adminRole = new Role("Admin");
        jdbcRoleDao.create(adminRole);
        User adminUser = new User("Admin", "1234", "alexru", "Alex", "Last",
                Date.valueOf("1997-04-29"), adminRole.getId());
        jdbcUserDao.create(adminUser);
    }
}

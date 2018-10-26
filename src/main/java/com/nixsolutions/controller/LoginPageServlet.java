package com.nixsolutions.controller;
import com.nixsolutions.service.impl.Role;
import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginPageServlet extends HttpServlet {

    private JdbcUserDao jdbcUserDao = new JdbcUserDao();
    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        System.out.println("session id from login " + session.getId() + ", " +session.getAttribute("login"));

        session.setAttribute("login",req.getParameter("login"));
        session.setAttribute("password",req.getParameter("password"));

        List<User> users = jdbcUserDao.findAll();
        System.out.println("I am in doPost");
        User user;
        String login = (String) session.getAttribute("login");
        String password = (String) session.getAttribute("password");

        System.out.println("login: " + session.getAttribute("login"));
        System.out.println("pass: "+ session.getAttribute("password"));
        user = jdbcUserDao.findByLogin(login);
        createUser();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        System.out.println(user.getAge());
        req.setAttribute("users",users);
        req.setAttribute("firstName",firstName);
        req.setAttribute("lastName",lastName);
        if (user.getPassword().equals(password) && user.getRole_id()==1L){
            req.getRequestDispatcher("admin.jsp").forward(req,resp);
        }else if (user.getPassword().equals(password)&& user.getRole_id()==2L){
            req.getRequestDispatcher("user.jsp").forward(req,resp);
        }else {
            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }


    }
    private void createUser(){
        User adminUser = new User("User", "1234", "alexru", "Alex", "Last",
                java.sql.Date.valueOf("1997-04-29"), 2L);
        jdbcUserDao.create(adminUser);
    }

    @Override public void init() throws ServletException {
        List<User> users = jdbcUserDao.findAll();
    }
}

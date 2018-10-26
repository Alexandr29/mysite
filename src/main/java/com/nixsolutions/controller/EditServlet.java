package com.nixsolutions.controller;

import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hi");
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("i am in edit Post");
        String login = req.getParameter("login");
        String firstname = req.getParameter("firstname");
        System.out.println(login);

        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        User user = jdbcUserDao.findByLogin(login);
        user.setFirstName(firstname);


        jdbcUserDao.update(user);
        req.getRequestDispatcher("/welcome").forward(req,resp);
    }

}

package com.nixsolutions.controller;

import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {

    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("i am in delete doGet");
        String login = req.getParameter("login");
        System.out.println(login);

        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        User user = jdbcUserDao.findByLogin(login);
        System.out.println(user.getId());
        jdbcUserDao.remove(user);
        req.getRequestDispatcher("/welcome").forward(req,resp);


    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

    }
}

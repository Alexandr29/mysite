package com.nixsolutions.controller;

import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {
    private JdbcUserDao jdbcUserDao = new JdbcUserDao();
    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        String logintodelete = req.getParameter("logintodelete");
        User user = jdbcUserDao.findByLogin(logintodelete);
        jdbcUserDao.remove(user);

        req.setAttribute("users", jdbcUserDao.findAll());
        resp.sendRedirect("/admin");

    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

    }
}

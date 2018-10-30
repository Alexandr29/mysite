package com.nixsolutions.controller;

import com.nixsolutions.service.hibernate.HibernateUserDao;
import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    private HibernateUserDao hibernateUserDao = new HibernateUserDao();
    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        String logintodelete = req.getParameter("logintodelete");
        User user = hibernateUserDao.findByLogin(logintodelete);
        hibernateUserDao.remove(user);

        req.setAttribute("users", hibernateUserDao.findAll());
        resp.sendRedirect("/admin");

    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

    }
}

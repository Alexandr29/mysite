package com.nixsolutions.controller;

import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/create")
public class CreateController  extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req,resp);
    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        System.out.println(login);
        String password = req.getParameter("password");
        String passwordagain = req.getParameter("passwordagain");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String date = req.getParameter("date");
        System.out.println(date + " from request");

        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        User user = new User();
        user.setLogin(login);
        if (password.equals(passwordagain)){
            user.setPassword(password);
        }else {
            System.out.println("пароли не одинаковые");
        }
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setBirthday(java.sql.Date.valueOf(date));
        user.setRole_id(1L);

        System.out.println(user.toString());



        jdbcUserDao.create(user);
        System.out.println("after: " + user.getBirthday());
        req.setAttribute("users",jdbcUserDao.findAll());
        req.setAttribute("login", login);
        resp.sendRedirect("/admin");
    }
}

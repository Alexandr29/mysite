package com.nixsolutions.controller;

import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
@WebServlet(urlPatterns = "/edit")
public class EditServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hi");
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordagain = req.getParameter("passwordagain");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String date = req.getParameter("date");
        System.out.println(login);

        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        User user = jdbcUserDao.findByLogin(login);

//        if (password.equals(passwordagain)){
//            user.setPassword(password);
//        }else {
//            System.out.println("пароли не одинаковые");
//        }
        user.setFirstName(firstname);
        user.setLastName(lastname);
        System.out.println(date);
        System.out.println(Date.valueOf(date));
        user.setBirthday(Date.valueOf(date));
        System.out.println(user.getAge());



        jdbcUserDao.update(user);
        req.setAttribute("users",jdbcUserDao.findAll());
        req.setAttribute("login", login);
        resp.sendRedirect("/admin");
    }

}

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
import java.util.List;

@WebServlet(urlPatterns = "/edit")
public class EditServlet extends HttpServlet {
    private JdbcUserDao jdbcUserDao = new JdbcUserDao();
    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        String logintoedit = req.getParameter("logintoedit");
        User user = jdbcUserDao.findByLogin(logintoedit);

        req.setAttribute("logintoedit",user.getLogin());
        req.setAttribute("passwordtoedit",user.getPassword());
        req.setAttribute("firstnametoedit",user.getFirstName());
        req.setAttribute("lastnametoedit",user.getLastName());
        req.setAttribute("birthdaytoedit",user.getBirthday());
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
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
        System.out.println(req.getSession().getAttribute("login") + " this is session login");
        System.out.println(date + " from request");

        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        User user = jdbcUserDao.findByLogin(login);

        user.setLogin(login);
        if (password.equals(passwordagain)){
            user.setPassword(password);
        }else {
            System.out.println("пароли не одинаковые");
        }
        user.setFirstName(firstname);
        user.setLastName(lastname);
        System.out.println("before: " + user.getBirthday());
        user.setBirthday(java.sql.Date.valueOf(date));
        user.setRole_id(2L);
        System.out.println(user.getAge());



        jdbcUserDao.update(user);
        System.out.println("after: " + user.getBirthday());
        req.setAttribute("users",jdbcUserDao.findAll());
        req.setAttribute("thislogin", req.getAttribute("login"));
        resp.sendRedirect("/admin");
    }

}

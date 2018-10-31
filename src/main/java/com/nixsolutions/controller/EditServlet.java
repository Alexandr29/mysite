package com.nixsolutions.controller;

import com.nixsolutions.service.hibernate.HibernateRoleDao;
import com.nixsolutions.service.hibernate.HibernateUserDao;
import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcRoleDao;
import com.nixsolutions.service.jdbc.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/edit") public class EditServlet
        extends HttpServlet {
    private HibernateUserDao hibernateUserDao = new HibernateUserDao();
    private HibernateRoleDao hibernateRoleDao = new HibernateRoleDao();

    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", hibernateRoleDao.findAll());
        String logintoedit = req.getParameter("logintoedit");
        User user = hibernateUserDao.findByLogin(logintoedit);
        req.setAttribute("logintoedit", user.getLogin());
        req.setAttribute("passwordtoedit", user.getPassword());
        req.setAttribute("firstnametoedit", user.getFirstName());
        req.setAttribute("lastnametoedit", user.getLastName());
        req.setAttribute("emailtoedit", user.getEmail());
        req.setAttribute("birthdaytoedit", user.getBirthday());
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordagain = req.getParameter("passwordagain");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String date = req.getParameter("date");
        String roleid = req.getParameter("rolevalue");

        int result = isValidData(login, password, passwordagain, firstname, lastname,email,
                date, Long.valueOf(roleid));

        if (result==1) {
            req.setAttribute("users", hibernateUserDao.findAll());

            req.setAttribute("thislogin", req.getAttribute("login"));
            resp.sendRedirect("/admin");
        } else {
            req.setAttribute("logintoedit", login);
            req.setAttribute("errorMessage", "password are not equals");
            doGet(req, resp);
        }

        //        req.setAttribute("users", jdbcUserDao.findAll());
        //        req.setAttribute("thislogin", req.getAttribute("login"));
        //        resp.sendRedirect("/admin");
    }

    int isValidData(String login, String password, String passwordagain,
            String firstname, String lastname, String email, String birthday,
            Long roleid) {

        if (login != "" && password != "" && firstname != ""
                && lastname != "" && birthday != "" && roleid != null
                && password.equals(passwordagain)) {
            User user = new User(login,password,firstname,lastname,email,Date.valueOf(birthday),roleid);
            user.setId(hibernateUserDao.findByLogin(login).getId());
            hibernateUserDao.update(user);
            return 1;
        } else {
            return 3;
        }}

}

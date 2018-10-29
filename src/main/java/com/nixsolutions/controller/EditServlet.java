package com.nixsolutions.controller;

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
    private JdbcUserDao jdbcUserDao = new JdbcUserDao();
    private JdbcRoleDao jdbcRoleDao = new JdbcRoleDao();

    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        String logintoedit = req.getParameter("logintoedit");
        User user = jdbcUserDao.findByLogin(logintoedit);
        req.setAttribute("roles", jdbcRoleDao.findAll());
        System.out.println("list roles: " + jdbcRoleDao.findAll());
        req.setAttribute("logintoedit", user.getLogin());
        req.setAttribute("passwordtoedit", user.getPassword());
        req.setAttribute("firstnametoedit", user.getFirstName());
        req.setAttribute("lastnametoedit", user.getLastName());
        req.setAttribute("birthdaytoedit", user.getBirthday());
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
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
        Long roleid = jdbcRoleDao.findByName(req.getParameter("rolevalue"))
                .getId();

        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        if (isValidData(login, password, passwordagain, firstname, lastname,
                date, roleid)) {
            req.setAttribute("users", jdbcUserDao.findAll());
            req.setAttribute("thislogin", req.getAttribute("login"));
            resp.sendRedirect("/admin");
        } else {
            req.setAttribute("logintoedit", login);
            req.setAttribute("errorMessage", "passwords are not equals ");
            doGet(req, resp);
        }

        //        req.setAttribute("users", jdbcUserDao.findAll());
        //        req.setAttribute("thislogin", req.getAttribute("login"));
        //        resp.sendRedirect("/admin");
    }

    boolean isValidData(String login, String password, String passwordagain,
            String firstname, String lastname, String birthday, Long roleid) {

        for (User user1 : jdbcUserDao.findAll()) {
            if (user1.getLogin().equals(login)) {
                return false;
            }
        }

        if (login != "" && password != "" && firstname != "" && lastname != ""
                && birthday != "" && roleid != null && password.equals(passwordagain)) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setBirthday(Date.valueOf(birthday));
            user.setBirthday(Date.valueOf(birthday));
            user.setRole_id(roleid);
            jdbcUserDao.create(user);
            return true;
        } else {
            return false;
        }
    }

}

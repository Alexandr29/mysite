package com.nixsolutions.controller;

import com.nixsolutions.service.hibernate.HibernateUserDao;
import com.nixsolutions.service.impl.Role;
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

@WebServlet(urlPatterns = "/create") public class CreateServlet
        extends HttpServlet {
    private HibernateUserDao hibernateUserDao = new HibernateUserDao();



    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("roles", hibernateUserDao.findAll());
        req.getRequestDispatcher("create.jsp").forward(req, resp);
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
        Long roleid = hibernateUserDao.findByLogin(req.getParameter("rolevalue"))
                .getId();

        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        int result = isValidData(login, password, passwordagain, firstname, lastname,email,
                date, roleid);

        if (result==1) {
            req.setAttribute("users", jdbcUserDao.findAll());
            req.setAttribute("thislogin", req.getAttribute("login"));
            resp.sendRedirect("/admin");
        } else if (result==2){
            req.setAttribute("logintoedit", login);
            req.setAttribute("errorMessage", "login is already use");
            doGet(req, resp);
        }else {
            req.setAttribute("logintoedit", login);
            req.setAttribute("errorMessage", "password are not equals");
            doGet(req, resp);
        }

        //        req.setAttribute("users", jdbcUserDao.findAll());
        //        req.setAttribute("thislogin", req.getAttribute("login"));
        //        resp.sendRedirect("/admin");
    }

    int isValidData(String login, String password, String passwordagain,
            String firstname, String lastname,String email, String birthday, Long roleid) {

        for (User user1:hibernateUserDao.findAll()) {
            if (user1.getLogin().equals(login)){
                return 2;
            }}

        if (login != "" && password != "" && firstname != ""
                && lastname != "" && birthday != "" && roleid != null
                && password.equals(passwordagain)) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setEmail(email);
            user.setBirthday(Date.valueOf(birthday));
            user.setBirthday(Date.valueOf(birthday));
            user.setRole_id(roleid);
            hibernateUserDao.create(user);
            return 1;
        } else {
            return 3;
        }
















//        for (User obj : jdbcUserDao.findAll()) {
//            if (obj.getLogin().equals(login)) {
//                return false;
//            } else if (login != "" && password != "" && firstname != ""
//                    && lastname != "" && birthday != "" && roleid != null
//                    && password.equals(passwordagain)) {
//
//
//            } else {
//                return false;
//            }
//        }return false;

    }
}
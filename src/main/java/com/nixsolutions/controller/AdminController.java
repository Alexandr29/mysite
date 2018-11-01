package com.nixsolutions.controller;

import com.nixsolutions.service.hibernate.HibernateRoleDao;
import com.nixsolutions.service.hibernate.HibernateUserDao;
import com.nixsolutions.service.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Controller public class AdminController {
    private HibernateUserDao hibernateUserDao;
    private HibernateRoleDao hibernateRoleDao;

    @Autowired public AdminController(HibernateUserDao hibernateUserDao,
            HibernateRoleDao hibernateRoleDao) {
        this.hibernateUserDao = hibernateUserDao;
        this.hibernateRoleDao = hibernateRoleDao;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET) public ModelAndView admin(
            HttpServletRequest req) {
        req.setAttribute("login", req.getSession().getAttribute("login"));
        req.setAttribute("roles", hibernateRoleDao.findAll());
        req.setAttribute("users", hibernateUserDao.findAll());
        return new ModelAndView("admin");
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET) public ModelAndView createGet(
            HttpServletRequest req) {
        req.setAttribute("roles", hibernateRoleDao.findAll());
        return new ModelAndView("create");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST) public ModelAndView createPost(
            HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordagain = req.getParameter("passwordagain");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String date = req.getParameter("date");
        String roleid = req.getParameter("rolevalue");

        int result = isValidData(login, password, passwordagain, firstname,
                lastname, email, date, Long.valueOf(roleid));
        if (result == 1) {
            req.setAttribute("users", hibernateUserDao.findAll());
            req.setAttribute("thislogin", req.getAttribute("login"));
            return new ModelAndView("admin");
        } else if (result == 2) {
            req.setAttribute("logintoedit", login);
            req.setAttribute("errorMessage", "login is already use");
            return new ModelAndView("create");
        } else {
            req.setAttribute("logintoedit", login);
            req.setAttribute("errorMessage", "password are not equals");
            return new ModelAndView("create");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET) public ModelAndView delete(
            HttpServletRequest req) {
        String logintodelete = req.getParameter("logintodelete");
        User user = hibernateUserDao.findByLogin(logintodelete);
        hibernateUserDao.remove(user);
        req.setAttribute("users", hibernateUserDao.findAll());
        return new ModelAndView("admin");
    }

    private int isValidData(String login, String password, String passwordagain,
            String firstname, String lastname, String email, String birthday,
            Long roleid) {

        for (User user1 : hibernateUserDao.findAll()) {
            if (user1.getLogin().equals(login)) {
                return 2;
            }
        }

        if (login != "" && password != "" && firstname != "" && lastname != ""
                && birthday != "" && roleid != null && password
                .equals(passwordagain)) {
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
    }

}

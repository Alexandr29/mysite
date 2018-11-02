package com.nixsolutions.controller;

import com.nixsolutions.service.hibernate.HibernateRoleDao;
import com.nixsolutions.service.hibernate.HibernateUserDao;
import com.nixsolutions.service.impl.User;
import org.h2.engine.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller public class LoginController {
    private LoginService service;
    private HibernateUserDao hibernateUserDao;
    private HibernateRoleDao hibernateRoleDao;

    @Autowired public LoginController(LoginService loginService,
            HibernateUserDao hibernateUserDao,
            HibernateRoleDao hibernateRoleDao) {
        this.service = loginService;
        this.hibernateUserDao = hibernateUserDao;
        this.hibernateRoleDao = hibernateRoleDao;
    }

    @RequestMapping(method = RequestMethod.GET) public ModelAndView login(
            HttpServletResponse response) throws IOException {
        return new ModelAndView("login");
    }

    @RequestMapping(method = RequestMethod.POST) public ModelAndView login(
            HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        boolean isValidUser = service.validateUser(login, password);
        boolean isAdmin = service.isAdmin(login);

        if (isValidUser) {
            if (isAdmin) {
                ModelAndView modelAndViewAdmin =  new ModelAndView("redirect:/admin");
                req.getSession().setAttribute("login", login);
                return modelAndViewAdmin;
            } else {
                ModelAndView modelAndViewUser =  new ModelAndView("redirect:/user");
                req.getSession().setAttribute("login", login);
                return modelAndViewUser;
            }
        } else {
            req.setAttribute("errorMessage", "Invalid Credentials!!");
            return new ModelAndView("login");
        }
    }

}

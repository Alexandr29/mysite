package com.nixsolutions.controller;

import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;
import com.nixsolutions.service.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller public class LoginController {

    @Autowired private UserService userService;
    @Autowired private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET, value = { "login",
            "/" }) public String showLoginPage(HttpSession session) {
        return "login";
    }

    @RequestMapping(method = GET, value = "/enter") public ModelAndView login(
            Principal principal, HttpSession session, HttpServletRequest req) {
        ModelAndView modelAndViewUser = new ModelAndView("redirect:/user");
        ModelAndView modelAndViewAdmin = new ModelAndView("redirect:/admin");
        String login = principal.getName();
        User userDB = userService.findByLogin(login);
        if (userDB.getRole_id() == 2L) {
            session.setAttribute("firstName", userDB.getFirstName());
            session.setAttribute("lastName", userDB.getLastName());
            return modelAndViewUser;
        }
        session.setAttribute("firstName", userDB.getFirstName());
        session.setAttribute("lastName", userDB.getLastName());

        return modelAndViewAdmin;
    }

    @RequestMapping(method = GET, value = { "*/logout",
            "/logout" }) public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(method = GET, value = "/admin") public String showUsersTable(
            Model model, HttpServletRequest req) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("login", req.getSession().getAttribute("firstname"));
        return "admin";
    }

    @RequestMapping(method = GET, value = "/error") public String showError() {
        return "redirect:/error";
    }
}

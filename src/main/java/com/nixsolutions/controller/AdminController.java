package com.nixsolutions.controller;

import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;
import com.nixsolutions.service.hibernate.HibernateRoleDao;
import com.nixsolutions.service.hibernate.HibernateUserDao;
import com.nixsolutions.service.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Controller public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

//    @RequestMapping(value = "/admin", method = RequestMethod.GET) public ModelAndView admin(
//            HttpServletRequest req) {
//        req.setAttribute("login", req.getSession().getAttribute("login"));
//        req.setAttribute("roles", roleService.findAll());
//        req.setAttribute("users", userService.findAll());
//        return new ModelAndView("admin");
//    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)

    public ModelAndView createGet(
            HttpServletRequest req) {
        req.setAttribute("roles", roleService.findAll());
        return new ModelAndView("create");
    }

    @PostMapping(value = "/create") public ModelAndView createPost(
            HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin");
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
            req.setAttribute("users", userService.findAll());
            req.setAttribute("thislogin", req.getAttribute("login"));
            return modelAndView;
        } else if (result == 2) {
            req.setAttribute("roles", roleService.findAll());
            req.setAttribute("logintoedit", login);
            req.setAttribute("errorMessage", "login is already use");
            return new ModelAndView("create");
        } else {
            req.setAttribute("logintoedit", login);
            req.setAttribute("roles", roleService.findAll());
            req.setAttribute("errorMessage", "password are not equals");
            return new ModelAndView("create");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET) public ModelAndView delete(
            HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin");
        String logintodelete = req.getParameter("logintodelete");

        User user = userService.findByLogin(logintodelete);
        userService.remove(logintodelete);
        modelAndView.addObject("users", userService.findAll());

        return modelAndView;
    }

    @RequestMapping(value = "/edit/*", method = RequestMethod.GET) public ModelAndView editGet(
            HttpServletRequest req) {
        req.setAttribute("roles", roleService.findAll());
        String logintoedit = req.getParameter("logintoedit");
        User user = userService.findByLogin(logintoedit);
        req.setAttribute("logintoedit", user.getLogin());
        req.setAttribute("passwordtoedit", user.getPassword());
        req.setAttribute("firstnametoedit", user.getFirstName());
        req.setAttribute("lastnametoedit", user.getLastName());
        req.setAttribute("emailtoedit", user.getEmail());
        req.setAttribute("birthdaytoedit", user.getBirthday());
        return new ModelAndView("edit");
    }
    @PostMapping("/registration")
    protected ModelAndView regisrtarton(HttpServletRequest req){
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordagain = req.getParameter("passwordagain");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String date = req.getParameter("date");
        String roleid = String.valueOf(2L);

        int result = isValidData(login, password, passwordagain, firstname,
                lastname, email, date, Long.valueOf(roleid));

        if (result == 1) {
            req.setAttribute("errorMessage", "Registration has been successful. Click Login to enter your account");
            req.setAttribute("login", req.getAttribute("login"));
            return modelAndView;
        } else if (result == 2) {
            req.setAttribute("roles", roleService.findAll());
            req.setAttribute("logintoedit", login);
            req.setAttribute("errorMessage", "login is already use");
            return new ModelAndView("Registration");
        } else {
            req.setAttribute("logintoedit", login);
            req.setAttribute("roles", roleService.findAll());
            req.setAttribute("errorMessage", "password are not equals");
            return new ModelAndView("Registration");
        }
    }

    @RequestMapping(value = "/edit/*", method = RequestMethod.POST) public ModelAndView editPost(
            HttpServletRequest req, Model model) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordagain = req.getParameter("passwordagain");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String date = req.getParameter("date");
        String roleid = req.getParameter("rolevalue");

        System.out.println("Hello" + model.toString());

        boolean result = isValidData2(login, password, passwordagain, firstname,
                lastname, email, date, Long.valueOf(roleid));

        if (result) {
            ModelAndView modelAndView = new ModelAndView("redirect:/admin");
            req.setAttribute("users", userService.findAll());
            req.setAttribute("thislogin", req.getAttribute("login"));
            return modelAndView;
        } else {
            req.setAttribute("roles", roleService.findAll());
            req.setAttribute("logintoedit", login);
            req.setAttribute("errorMessage", "password are not equals");
            return new ModelAndView("edit");
        }

    }

    boolean isValidData2(String login, String password, String passwordagain,
            String firstname, String lastname, String email, String birthday,
            Long roleid) {

        if (login != "" && password != "" && firstname != "" && lastname != ""
                && birthday != "" && roleid != null && password
                .equals(passwordagain)) {
            User user = new User(login, password, email, firstname, lastname,
                    Date.valueOf(birthday), roleid);
            user.setId(userService.findByLogin(login).getId());

            System.out.println(user.toString());
            userService.update(user);
            return true;
        } else {
            return false;
        }
    }

    protected int isValidData(String login, String password, String passwordagain,
            String firstname, String lastname, String email, String birthday,
            Long roleid) {
        for (User user1 : userService.findAll()) {
            if (user1.getLogin().equals(login)) {
                System.out.println(user1.getLogin() + " " + login);
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
            userService.create(user);
            return 1;
        } else {
            return 3;
        }
    }

}

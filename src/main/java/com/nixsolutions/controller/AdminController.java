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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;

@Controller public class AdminController {

    @Autowired private UserService userService;
    @Autowired private RoleService roleService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)

    public String showRegistration(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "create";
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

    @GetMapping(value = "/edit") public String edit(Model model,
            @ModelAttribute("logintoedit") String login) {
        User user = userService.findByLogin(login);

        System.out.println(login);
        System.out.println(user.toString() + "!!!!!!!");
        model.addAttribute("login", user.getLogin());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        return "edit";
    }

    @PostMapping("/registration") protected String registrarton(
            @Valid User user, BindingResult bindingResult, Model model) {


        if (!isValidLogin(user)) {
            FieldError loginAlreadyUse = new FieldError("login", "login",
                    "login already in use");
            bindingResult.addError(loginAlreadyUse);
        }
        if (passwordNotEquals(user)) {
            FieldError passwordNotEquals = new FieldError("password",
                    "password", "password not equals");
            bindingResult.addError(passwordNotEquals);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("error",
                    bindingResult.getFieldError().getDefaultMessage());
            return "Registration";
        }

        try {
            user.setRole_id(2L);
            userService.create(user);
        } catch (IllegalArgumentException e) {
        }
        return "login";

    }

    @PostMapping("/create") protected String create(@Valid User user,
            BindingResult bindingResult, Model model) {

        if (!isValidLogin(user)) {
            FieldError loginAlreadyUse = new FieldError("login", "login",
                    "login already in use");
            bindingResult.addError(loginAlreadyUse);
        }
        if (passwordNotEquals(user)) {
            FieldError passwordNotEquals = new FieldError("password",
                    "password", "password not equals");
            bindingResult.addError(passwordNotEquals);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("error",
                    bindingResult.getFieldError().getDefaultMessage());
            return "create";
        }
        try {
            userService.create(user);
            model.addAttribute("error", "user successfully created");
            model.addAttribute("users", userService.findAll());
        } catch (IllegalArgumentException e) {
            return "create";
        }
        return "admin";
    }

    @PostMapping("/edit") protected String edit(@Valid User user,
            BindingResult bindingResult, Model model) {
        String login = user.getLogin();
        User user1 = userService.findByLogin(login);
        user.setId(user1.getId());
        System.out.println("i am in post");

        if (passwordNotEquals(user)) {
            FieldError passwordNotEquals = new FieldError("password",
                    "password", "password not equals");
            bindingResult.addError(passwordNotEquals);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("logintoedit", login);
            model.addAttribute("error",
                    bindingResult.getFieldError().getDefaultMessage());
            return "edit";
        }
        try {
            userService.update(user);
            model.addAttribute("users", userService.findAll());
            System.out.println("i am in post 3");
            System.out.println("success");
        } catch (IllegalArgumentException e) {
            System.out.println("i am in post 4");
            return "redirect:/edit";
        }
        return "admin";
    }

    protected boolean isValidLogin(User user) {
        for (User user1 : userService.findAll()) {
            if (user1.getLogin().equals(user.getLogin())) {
                System.out.println(user1.getLogin() + " " + user.getLogin());
                return false;
            }
        }
        return true;
    }

    public boolean passwordNotEquals(User user) {
        System.out.println(user.getPasswordagain());
        return !(user.getPassword().equals(user.getPasswordagain()));
    }

}

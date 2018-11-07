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

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller public class AdminController {

    @Autowired private UserService userService;
    @Autowired private RoleService roleService;

    @GetMapping(value = "/create") public String create(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "create";
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
            model.addAttribute("roles", roleService.findAll());
            return "create";
        }
        try {
            userService.create(user);
            model.addAttribute("error", "user successfully created");
            model.addAttribute("users", userService.findAll());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getCause());
        }
        return "admin";
    }

    @GetMapping(value = "/registration") public String showRegistration(
            Model model) {
        return "Registration";
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
            throw new RuntimeException(e.getCause());
        }
        model.addAttribute("error",
                "Registration successful.Enter your account");
        return "login";
    }

    @GetMapping(value = "/edit") public String edit(Model model,
            @ModelAttribute("logintoedit") String login) {
        User user = userService.findByLogin(login);
        model.addAttribute("login", user.getLogin());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        return "edit";
    }

    @PostMapping("/edit") protected String edit(@Valid User user,
            BindingResult bindingResult, Model model) {
        String login = user.getLogin();
        User user1 = userService.findByLogin(login);
        user.setId(user1.getId());

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
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getCause());
        }
        model.addAttribute("error", "Successfully update");
        return "admin";
    }

    protected boolean isValidLogin(User user) {
        for (User user1 : userService.findAll()) {
            if (user1.getLogin().equals(user.getLogin())) {
                return false;
            }
        }
        return true;
    }

    public boolean passwordNotEquals(User user) {
        return (!user.getPassword().equals(user.getPasswordagain()));
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
}

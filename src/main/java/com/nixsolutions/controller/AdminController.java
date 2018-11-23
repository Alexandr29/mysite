package com.nixsolutions.controller;

import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;
import com.nixsolutions.service.impl.Role;
import com.nixsolutions.service.impl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/admin")
    public String showUsersTable(
            Model model, HttpServletRequest req) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "create";
    }

    @PostMapping("/create")
    protected String create(@Valid User user,
            BindingResult bindingResult, Model model,
            @RequestParam("passwordagain") String passwordAgain) {

        if (!isValidLogin(user)) {
            FieldError loginAlreadyUse = new FieldError("login", "login",
                    "login already in use");
            bindingResult.addError(loginAlreadyUse);
        }
        if (!passwordAgain.equals(user.getPassword())) {
            FieldError passwordNotEquals = new FieldError("password",
                    "password", "password not equals");
            bindingResult.addError(passwordNotEquals);
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("error",
                    bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("user",user);
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

    @GetMapping(value = "/registration")
    public String showRegistration(
            Model model) {
        return "Registration";
    }

    @PostMapping("/registration")
    protected String registrarton(
            @Valid User user, BindingResult bindingResult, Model model,
            @RequestParam("passwordagain") String passwordAgain) {

        if (!isValidLogin(user)) {
            FieldError loginAlreadyUse = new FieldError("login", "login",
                    "login already in use");
            bindingResult.addError(loginAlreadyUse);
        }
        if (!passwordAgain.equals(user.getPassword())) {
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
            user.setRoleId(2L);
            userService.create(user);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getCause());
        }
        model.addAttribute("error",
                "Registration successful.Enter your account");
        return "login";
    }

    @GetMapping(value = "/edit")
    public String edit(Model model,
            @ModelAttribute("logintoedit") String login) {
        User user = userService.findByLogin(login);
        model.addAttribute("login", user.getLogin());
        model.addAttribute("role", roleService.findById(user.getRoleId()).getId());
        model.addAttribute("rolename", roleService.findById(user.getRoleId()).getName());
        model.addAttribute("user", user);
        List<Role> list = roleService.findAll();
        list.remove(roleService.findById(user.getRoleId()));
        model.addAttribute("roles", list);
        return "edit";
    }

    @PostMapping("/edit")
    protected String edit(@Valid User user,
            BindingResult bindingResult, Model model,
            @RequestParam("passwordagain") String passwordAgain) {
        String login = user.getLogin();
        User user1 = userService.findByLogin(login);
        user.setId(user1.getId());

        if (!passwordAgain.equals(user.getPassword())) {
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

    @GetMapping(value = "/delete")
    public ModelAndView delete(
            HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin");
        String logintodelete = req.getParameter("logintodelete");
        User user = userService.findByLogin(logintodelete);
        userService.remove(user);
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }
}

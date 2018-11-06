package com.nixsolutions.controller;

import com.nixsolutions.service.UserService;
import com.nixsolutions.service.impl.User;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET, value = {"login", "/"})
    public String showLoginPage(HttpSession session) {
        return "login";
    }


    @ResponseBody
    @RequestMapping(method = GET, value = {"/j_spring_security_check"})
    public String login(Principal principal, HttpSession session) {
        System.out.println("i am in succesful");
        String login = principal.getName();
        User userDB = userService.findByLogin(login);

        if (userDB.getRole_id().equals("USER")) {
            session.setAttribute("firstName", userDB.getFirstName());
            return "user";
        }
        session.setAttribute("firstName", userDB.getFirstName());
        session.setAttribute("lastName", userDB.getLastName());

        return "redirect:/admin";
    }

    @RequestMapping(method = GET, value = {"*/logout", "/logout"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(method = GET, value = "/admin")
    public String showUsersTable(Model model) {
        model.addAttribute("users", userService.findAll());

        return "admin";
    }

    @RequestMapping(method = GET, value = "/register")
    public String showRegistration(Model model) {
        model.addAttribute("User", new User());

        return "Registration";
    }

//    @RequestMapping(method = POST, value = "/register")
//    public String register(@ModelAttribute("userToRegister") @Valid User user, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("User", user);
//            return "Registration";
//        }
//        try {
//            userService.create(user);
//        } catch (IllegalArgumentException e) {
//            // TODO this login is already in use! - добавить
//            // TODO не показывает ошибки на странице, возможно проблема с JSP
//        }
//        return "Authorization";
//    }

    @RequestMapping(method = GET, value = "/error")
    public String showError() {

        return "Error";
    }
}

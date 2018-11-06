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
import org.springframework.web.servlet.ModelAndView;

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


    @RequestMapping(method = GET, value = "/success")
    public ModelAndView login(Principal principal, HttpSession session) {
        ModelAndView modelAndViewUser = new ModelAndView("redirect:/user");
        ModelAndView modelAndViewAdmin = new ModelAndView("redirect:/admin");
        System.out.println("i am in succesful");
        String login = principal.getName();
        User userDB = userService.findByLogin(login);

        System.out.println(userDB.toString());

        if (userDB.getRole_id().equals("2")) {
            session.setAttribute("firstName", userDB.getFirstName());
            return modelAndViewUser;
        }
        session.setAttribute("firstName", userDB.getFirstName());
        session.setAttribute("lastName", userDB.getLastName());

        return modelAndViewAdmin;
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

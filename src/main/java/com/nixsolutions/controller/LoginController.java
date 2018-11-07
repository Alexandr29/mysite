package com.nixsolutions.controller;

import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;
import com.nixsolutions.service.impl.Role;
import com.nixsolutions.service.impl.User;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET, value = {"login", "/"})
    public String showLoginPage(HttpSession session) {
        return "login";
    }



    @RequestMapping(method = GET, value = "/success")
    public ModelAndView login(Principal principal, HttpSession session, HttpServletRequest req) {
        System.out.println(" i a m in success");
        ModelAndView modelAndViewUser = new ModelAndView("redirect:/user");
        ModelAndView modelAndViewAdmin = new ModelAndView("redirect:/admin");
        System.out.println("i am in succesful");
        String login = principal.getName();
        User userDB = userService.findByLogin(login);

        System.out.println(userDB.toString());

        if (userDB.getRole_id()==2L) {
            session.setAttribute("firstName", userDB.getFirstName());
            session.setAttribute("lastName", userDB.getLastName());

            return modelAndViewUser;
        }
        session.setAttribute("firstName", userDB.getFirstName());
        session.setAttribute("lastName", userDB.getLastName());

        return modelAndViewAdmin;
    }

//    @GetMapping("/registration")
//    protected String registration(Model model){
//        return "Registration";
//    }

    @RequestMapping(method = GET, value = {"*/logout", "/logout"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(method = GET, value = "/admin")
    public String showUsersTable(Model model,HttpServletRequest req) {
        model.addAttribute("users",userService.findAll());
        model.addAttribute("login",req.getSession().getAttribute("firstname"));
//        req.setAttribute("users", userService.findAll());
//        req.setAttribute("login",req.getSession().getAttribute("firstName"));
        return "admin";
    }

    @RequestMapping(method = GET, value = "/registration")
    public String showRegistration(Model model) {
       model.addAttribute("rolesid", 2L);
        return "Registration";
    }


    @RequestMapping(method = GET, value = "/error")
    public String showError() {

        return "redirect:/error";
    }
}

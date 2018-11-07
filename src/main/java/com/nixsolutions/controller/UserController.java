package com.nixsolutions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/user") @Controller public class UserController {

    @RequestMapping(method = RequestMethod.GET) protected ModelAndView userGet(
            HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("login", req.getSession().getAttribute("firstName"));
        return new ModelAndView("user");
    }

    @RequestMapping(method = RequestMethod.POST) protected ModelAndView userPost(
            HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        modelAndView.addObject("login", req.getSession().getAttribute("login"));
        return modelAndView;
    }

}

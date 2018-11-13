package com.nixsolutions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView userGet(
            HttpServletRequest req, HttpServletResponse resp) {
        return new ModelAndView("user");
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView userPost(
            HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        return modelAndView;
    }

}

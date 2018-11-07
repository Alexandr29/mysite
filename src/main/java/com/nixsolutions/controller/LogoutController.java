package com.nixsolutions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller public class LogoutController {
    @RequestMapping(value = "/logout") private ModelAndView logout(
            HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        req.getSession().invalidate();
        return modelAndView;
    }
}

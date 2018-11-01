package com.nixsolutions.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(value = "/logout")
public class LogoutController {

  private ModelAndView logout(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        return new ModelAndView("login");
    }
}

package com.nixsolutions.controller;

import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;
import com.nixsolutions.service.hibernate.HibernateRoleDao;
import com.nixsolutions.service.hibernate.HibernateUserDao;
import com.nixsolutions.service.impl.User;
import org.h2.engine.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller public class LoginController implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        System.out.println("I am here");
        SecurityContextImpl sci = (SecurityContextImpl) request.getSession()
                .getAttribute("SPRING_SECURITY_CONTEXT");
        if (sci != null) {
            UserDetails user = (UserDetails) sci.getAuthentication()
                    .getPrincipal();
            System.out.println(user.toString());
            user.getAuthorities().forEach(a -> {
                if (a.getAuthority().equals("ADMIN")) {
                    try {
                        response.sendRedirect("/admin");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (a.getAuthority().equals("USER")) {
                    try {
                        response.sendRedirect("/user");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return true;
    }

}

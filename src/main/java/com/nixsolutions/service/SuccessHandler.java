package com.nixsolutions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Override public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication)
            throws IOException, ServletException {

        System.out.println("I am here 2");

        Set<String> roles = AuthorityUtils
                .authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ADMIN")) {
            httpServletResponse.sendRedirect("admin");
        } else {
            httpServletResponse.sendRedirect("user");
        }

    }
}


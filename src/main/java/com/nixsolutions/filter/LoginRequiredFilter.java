package com.nixsolutions.filter;

import javax.servlet.*;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginRequiredFilter implements Filter {
    @Override public void init(FilterConfig filterConfig)
            throws ServletException {

    }

    @Override public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getSession().getAttribute("login") != null) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            request.getRequestDispatcher("/login").forward(servletRequest,
                    servletResponse);
        }

    }

    @Override public void destroy() {

    }
}

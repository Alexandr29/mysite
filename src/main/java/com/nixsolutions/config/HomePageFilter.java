//package com.nixsolutions.config;
//
//import org.springframework.security.core.context.SecurityContextImpl;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class HomePageFilter implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//            HttpServletResponse response, Object handler) throws Exception {
//
//        System.out.println(" i am in preHandle");
//        String login = request.getParameter("j_username");
//        System.out.println(login);
////        SecurityContextImpl sci = (SecurityContextImpl) request.getSession()
////                .getAttribute("SPRING_SECURITY_CONTEXT");
////        if (sci != null) {
////            UserDetails user = (UserDetails) sci.getAuthentication()
////                    .getPrincipal();
////            user.getAuthorities().forEach(a -> {
////                if (a.getAuthority().equals("ADMIN")) {
////                    try {
////                        response.sendRedirect("/admin");
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                } else if (a.getAuthority().equals("2")) {
////                    try {
////                        response.sendRedirect("/user");
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                }
////            });
////        }
//        return true;
//    }
//
//}

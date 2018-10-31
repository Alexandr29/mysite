package com.nixsolutions.controller;
import com.nixsolutions.service.hibernate.HibernateUserDao;

import com.nixsolutions.service.impl.Role;
import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login") public class LoginPageServlet
        extends HttpServlet {
    private LoginService service = new LoginService();
    private HibernateUserDao hibernateUserDao = new HibernateUserDao();

    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        boolean isValidUser = service.validateUser(login, password);
        boolean isAdmin = service.isAdmin(login);

        if (isValidUser) {
            if(isAdmin){
                req.getSession().setAttribute("users",hibernateUserDao.findAll());
                req.getSession().setAttribute("login", login);
                resp.sendRedirect("/admin");
            } else {
                req.getSession().setAttribute("login", login);
                resp.sendRedirect("/user");
            }
        } else {
            req.setAttribute("errorMessage", "Invalid Credentials!!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
//        List<User> users = jdbcUserDao.findAll();
//        System.out.println("I am in doPost");
//        User user;
//        String login = (String) session.getAttribute("login");
//        String password = (String) session.getAttribute("password");
//
//        System.out.println("login: " + session.getAttribute("login"));
//        System.out.println("pass: "+ session.getAttribute("password"));
//        user = jdbcUserDao.findByLogin(login);
//        createUser();
//        String firstName = user.getFirstName();
//        String lastName = user.getLastName();
//        System.out.println(user.getAge());
//        req.setAttribute("users",users);
//        req.setAttribute("firstName",firstName);
//        req.setAttribute("lastName",lastName);
//        if (user.getPassword().equals(password) && user.getRole_id()==1L){
//            req.getRequestDispatcher("admin.jsp").forward(req,resp);
//        }else if (user.getPassword().equals(password)&& user.getRole_id()==2L){
//            req.getRequestDispatcher("user.jsp").forward(req,resp);
//        }else {
//            req.getRequestDispatcher("error.jsp").forward(req,resp);
//        }
    }

    @Override public void init() throws ServletException {
        List<User> users = hibernateUserDao.findAll();
    }
}

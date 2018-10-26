package com.nixsolutions.controller;

import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    private JdbcUserDao jdbcUserDao = new JdbcUserDao();

    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", jdbcUserDao.findAll());
        req.getRequestDispatcher("admin.jsp").forward(req, resp);
        //createUser();
    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
//        String users = req.getParameter("users");
//        if ("".equals(users)) {
//            req.setAttribute("errorMessage", "Enter a valid users");
//        } else {
//            //todoService.addTodo(todo);
//        }
                List<User> users = jdbcUserDao.findAll();
                System.out.println("I am in doPost");
                User user;
                String login = String.valueOf(req.getAttribute("login"));
                String password = (String) req.getAttribute("password");
        System.out.println(login + " " + password);
                user = jdbcUserDao.findByLogin(login);

                String firstName = user.getFirstName();
                String lastName = user.getLastName();
                System.out.println(user.getAge());
                req.setAttribute("users",users);
                req.setAttribute("firstName",firstName);
                req.setAttribute("lastName",lastName);
                if (user.getPassword().equals(password) && user.getRole_id()==1L){
                    req.getRequestDispatcher("admin.jsp").forward(req,resp);
                }else if (user.getPassword().equals(password)&& user.getRole_id()==2L){
                    req.getRequestDispatcher("user.jsp").forward(req,resp);
                }else {
                    req.getRequestDispatcher("error.jsp").forward(req, resp);
                }
        //request.setAttribute("todos", todoService.retrieveTodos());
        //request.getRequestDispatcher("/WEB-INF/views/todo.jsp").forward(request, response);
    }
    private void createUser(){
        User adminUser = new User("User", "1234", "alexru", "Alex", "Last",
                java.sql.Date.valueOf("1997-04-29"), 2L);
        jdbcUserDao.create(adminUser);
    }
}

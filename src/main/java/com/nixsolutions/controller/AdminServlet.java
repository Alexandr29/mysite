package com.nixsolutions.controller;

import com.nixsolutions.service.hibernate.HibernateRoleDao;
import com.nixsolutions.service.hibernate.HibernateUserDao;
import com.nixsolutions.service.impl.User;
import com.nixsolutions.service.jdbc.JdbcRoleDao;
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
    HibernateUserDao hibernateUserDao = new HibernateUserDao();
    private HibernateRoleDao hibernateRoleDao = new HibernateRoleDao();


    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("login",req.getSession().getAttribute("login"));
        req.setAttribute("roles", hibernateRoleDao.findAll());
        req.setAttribute("users", hibernateUserDao.findAll());
        //System.out.println(jdbcUserDao.findAll());
        //req.setAttribute("rolename",jdbcRoleDao.fundById(jdbcUserDao.findByLogin().getRole_id()).getName());

        req.getRequestDispatcher("admin.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
//        String users = req.getParameter("users");
//        if ("".equals(users)) {
//            req.setAttribute("errorMessage", "Enter a valid users");
//        } else {
//            //todoService.addTodo(todo);
//        }
                List<User> users = hibernateUserDao.findAll();
                User user;
                String login = String.valueOf(req.getAttribute("login"));
                String password = (String) req.getAttribute("password");
                user = hibernateUserDao.findByLogin(login);

                String firstName = user.getFirstName();
                String lastName = user.getLastName();
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
        hibernateUserDao.create(adminUser);
    }
}

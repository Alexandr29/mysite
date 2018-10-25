package com.nixsolutions.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hi");
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }

    @Override protected void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("i am in edit Post");
        req.getRequestDispatcher("/admin.jsp").forward(req,resp);
    }

}

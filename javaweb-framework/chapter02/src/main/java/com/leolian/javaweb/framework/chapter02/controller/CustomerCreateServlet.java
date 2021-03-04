package com.leolian.javaweb.framework.chapter02.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 
 * @author lianliang
 * @date 2018/11/7 13:51
 */
@WebServlet("/customer_servlet")
public class CustomerCreateServlet extends HttpServlet {

    private static final long serialVersionUID = 7253522076400572635L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    
}

package com.noteshare.easymock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        System.out.println("调用doPost方法");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        System.out.println("调用doGet方法");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username=" + username);
        System.out.println("password=" + password);
        System.out.println("servlet test success....");
    }
}

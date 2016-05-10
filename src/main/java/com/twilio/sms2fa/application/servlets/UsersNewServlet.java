package com.twilio.sms2fa.application.servlets;

import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class UsersNewServlet extends HttpServlet {

    public static final String WEB_INF_PAGES_USERS_NEW_JSP = "/WEB-INF/pages/users/new.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(WEB_INF_PAGES_USERS_NEW_JSP).forward(request, response);
    }

}

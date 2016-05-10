package com.twilio.sms2fa.application.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.twilio.sms2fa.domain.exception.WrongUserPasswordException;
import com.twilio.sms2fa.domain.service.LogIn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class SessionsServlet extends HttpServlet {

    private LogIn logIn;

    @Inject
    public SessionsServlet(LogIn logIn) {
        this.logIn = logIn;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            logIn.authenticate(email, password);
            response.sendRedirect("/confirmations/new");
        } catch (WrongUserPasswordException e){
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/pages/sessions/new.jsp").forward(request, response);
        }
    }
}

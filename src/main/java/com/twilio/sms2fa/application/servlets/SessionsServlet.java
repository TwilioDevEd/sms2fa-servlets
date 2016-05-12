package com.twilio.sms2fa.application.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.twilio.sms2fa.application.constants.ExternalResource;
import com.twilio.sms2fa.application.constants.InternalResource;
import com.twilio.sms2fa.domain.exception.WrongUserPasswordException;
import com.twilio.sms2fa.domain.model.User;
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
    public SessionsServlet(final LogIn logIn) {
        this.logIn = logIn;
    }

    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = logIn.authenticate(email, password);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(ExternalResource.CONFIRMATIONS_NEW.getPath());
        } catch (WrongUserPasswordException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(InternalResource.SESSIONS_NEW_JSP.getPath()).forward(request, response);
        }
    }
}

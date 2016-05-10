package com.twilio.sms2fa.application.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.twilio.sms2fa.domain.exception.WrongVerificationCodeException;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.service.ConfirmUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class ConfirmationsServlet extends HttpServlet {

    private ConfirmUser confirmUser;

    @Inject
    public ConfirmationsServlet(ConfirmUser confirmUser) {
        this.confirmUser = confirmUser;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String verificationCode = request.getParameter("verification_code");
            User user = (User) request.getSession().getAttribute("user");
            confirmUser.confirm(user, verificationCode);
            request.getSession().setAttribute("authenticated", true);
            response.sendRedirect("/secrets/");
        } catch (WrongVerificationCodeException e){
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher(ConfirmationsNewServlet.WEB_INF_PAGES_CONFIRMATIONS_NEW_JSP).forward(request, response);
        }
    }

}
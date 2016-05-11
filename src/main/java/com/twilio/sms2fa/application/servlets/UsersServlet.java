package com.twilio.sms2fa.application.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.twilio.sms2fa.application.constants.ExternalResource;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.service.CreateUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class UsersServlet extends HttpServlet {

    private CreateUser createUser;

    @Inject
    public UsersServlet(CreateUser createUser) {
        this.createUser = createUser;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        String password = request.getParameter("password");

        User user = createUser.create(new User(firstName, lastName, email, phoneNumber, password));
        request.getSession().setAttribute("user", user);
        response.sendRedirect(ExternalResource.CONFIRMATIONS_NEW.getPath());
    }
}
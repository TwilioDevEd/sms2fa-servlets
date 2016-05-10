package com.twilio.sms2fa.application.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class UsersServlet extends HttpServlet {

    private UserRepository userRepository;

    @Inject
    public UsersServlet(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        String password = request.getParameter("password");

        User user = userRepository.save(new User(firstName, lastName, email, phoneNumber, password));
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/confirmations/new");
    }
}
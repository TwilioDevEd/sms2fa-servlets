package com.twilio.sms2fa.infrastructure.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.twilio.sms2fa.application.servlets.UsersNewServlet;
import com.twilio.sms2fa.application.servlets.UsersServlet;
import com.twilio.sms2fa.domain.repository.UserRepository;
import com.twilio.sms2fa.infrastructure.repository.UserInMemoryRepository;

public class Sms2faGuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            public void configureServlets() {
                serve("/users/").with(UsersServlet.class);
                serve("/users/new").with(UsersNewServlet.class);

                bind(UserRepository.class).to(UserInMemoryRepository.class);
            }
        });
    }
}
package com.twilio.sms2fa.application.servlets;

import com.google.inject.servlet.ServletModule;
import com.twilio.sms2fa.domain.repository.UserRepository;
import com.twilio.sms2fa.infrastructure.repository.UserInMemoryRepository;

public class Sms2faServletModule extends ServletModule {
    @Override
    public void configureServlets() {
        serve("/users/").with(UserServlet.class);

        bind(UserRepository.class).to(UserInMemoryRepository.class);
    }
}

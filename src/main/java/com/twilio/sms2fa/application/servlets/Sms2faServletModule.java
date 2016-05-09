package com.twilio.sms2fa.application.servlets;

import com.google.inject.servlet.ServletModule;

public class Sms2faServletModule extends ServletModule {
    @Override
    public void configureServlets() {
        serve("/users/").with(UserServlet.class);
    }
}

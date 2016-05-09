package com.twilio.sms2fa.infrastructure.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.twilio.sms2fa.application.servlets.Sms2faServletModule;

public class Sms2faGuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new Sms2faServletModule());
    }
}
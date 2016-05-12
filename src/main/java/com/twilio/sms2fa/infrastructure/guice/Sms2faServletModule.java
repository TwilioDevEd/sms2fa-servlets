package com.twilio.sms2fa.infrastructure.guice;


import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;
import com.twilio.sms2fa.application.filters.AuthenticationFilter;
import com.twilio.sms2fa.application.servlets.ConfirmationsNewServlet;
import com.twilio.sms2fa.application.servlets.ConfirmationsServlet;
import com.twilio.sms2fa.application.servlets.LogoutServlet;
import com.twilio.sms2fa.application.servlets.SecretsServlet;
import com.twilio.sms2fa.application.servlets.SessionsNewServlet;
import com.twilio.sms2fa.application.servlets.SessionsServlet;
import com.twilio.sms2fa.application.servlets.UsersNewServlet;
import com.twilio.sms2fa.application.servlets.UsersServlet;

import static com.twilio.sms2fa.application.constants.ExternalResource.CONFIRMATIONS;
import static com.twilio.sms2fa.application.constants.ExternalResource.CONFIRMATIONS_NEW;
import static com.twilio.sms2fa.application.constants.ExternalResource.LOGOUT;
import static com.twilio.sms2fa.application.constants.ExternalResource.SECRETS;
import static com.twilio.sms2fa.application.constants.ExternalResource.SESSIONS;
import static com.twilio.sms2fa.application.constants.ExternalResource.SESSIONS_NEW;
import static com.twilio.sms2fa.application.constants.ExternalResource.USERS;
import static com.twilio.sms2fa.application.constants.ExternalResource.USERS_NEW;

public class Sms2faServletModule extends ServletModule {

    @Override
    public void configureServlets() {
        filter("/*").through(PersistFilter.class);
        filter(SECRETS.getPath()).through(AuthenticationFilter.class);

        serve(USERS.getPath()).with(UsersServlet.class);
        serve(USERS_NEW.getPath()).with(UsersNewServlet.class);
        serve(CONFIRMATIONS_NEW.getPath()).with(ConfirmationsNewServlet.class);
        serve(CONFIRMATIONS.getPath()).with(ConfirmationsServlet.class);
        serve(SECRETS.getPath()).with(SecretsServlet.class);
        serve(SESSIONS_NEW.getPath()).with(SessionsNewServlet.class);
        serve(SESSIONS.getPath()).with(SessionsServlet.class);
        serve(LOGOUT.getPath()).with(LogoutServlet.class);
    }

}

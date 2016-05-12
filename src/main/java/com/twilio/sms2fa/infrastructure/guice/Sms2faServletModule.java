package com.twilio.sms2fa.infrastructure.guice;

import com.google.inject.persist.PersistFilter;
import com.twilio.sms2fa.application.constants.ExternalResource;
import com.twilio.sms2fa.application.filters.AuthenticationFilter;
import com.twilio.sms2fa.application.servlets.*;

public class Sms2faServletModule extends com.google.inject.servlet.ServletModule {

    @Override
    public void configureServlets() {
        filter("/*").through(PersistFilter.class);
        filter(ExternalResource.SECRETS.getPath()).through(AuthenticationFilter.class);

        serve(ExternalResource.USERS.getPath()).with(UsersServlet.class);
        serve(ExternalResource.USERS_NEW.getPath()).with(UsersNewServlet.class);
        serve(ExternalResource.CONFIRMATIONS_NEW.getPath()).with(ConfirmationsNewServlet.class);
        serve(ExternalResource.CONFIRMATIONS.getPath()).with(ConfirmationsServlet.class);
        serve(ExternalResource.SECRETS.getPath()).with(SecretsServlet.class);
        serve(ExternalResource.SESSIONS_NEW.getPath()).with(SessionsNewServlet.class);
        serve(ExternalResource.SESSIONS.getPath()).with(SessionsServlet.class);
        serve(ExternalResource.LOGOUT.getPath()).with(LogoutServlet.class);
    }

}

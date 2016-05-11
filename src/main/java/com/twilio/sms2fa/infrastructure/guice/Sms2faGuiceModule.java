package com.twilio.sms2fa.infrastructure.guice;

import com.google.inject.Provides;
import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sms2fa.application.constants.ExternalResource;
import com.twilio.sms2fa.application.filters.AuthenticationFilter;
import com.twilio.sms2fa.application.servlets.*;
import com.twilio.sms2fa.domain.repository.UserRepository;
import com.twilio.sms2fa.domain.service.ConfirmUser;
import com.twilio.sms2fa.domain.service.CreateUser;
import com.twilio.sms2fa.domain.service.MessageSender;
import com.twilio.sms2fa.infrastructure.repository.UserInMemoryRepository;
import com.twilio.sms2fa.infrastructure.service.TwilioMessageSender;

public class Sms2faGuiceModule extends ServletModule {

    private ApplicationProperties applicationProperties = new ApplicationProperties();

    @Override
    public void configureServlets() {
        Names.bindProperties(binder(), applicationProperties);

        serve(ExternalResource.USERS.getPath()).with(UsersServlet.class);
        serve(ExternalResource.USERS_NEW.getPath()).with(UsersNewServlet.class);
        serve(ExternalResource.CONFIRMATIONS_NEW.getPath()).with(ConfirmationsNewServlet.class);
        serve(ExternalResource.CONFIRMATIONS.getPath()).with(ConfirmationsServlet.class);
        serve(ExternalResource.SECRETS.getPath()).with(SecretsServlet.class);
        serve(ExternalResource.SESSIONS_NEW.getPath()).with(SessionsNewServlet.class);
        serve(ExternalResource.SESSIONS.getPath()).with(SessionsServlet.class);
        serve(ExternalResource.LOGOUT.getPath()).with(LogoutServlet.class);

        filter(ExternalResource.SECRETS.getPath()).through(AuthenticationFilter.class);

        bind(UserRepository.class).to(UserInMemoryRepository.class);
        bind(MessageSender.class).to(TwilioMessageSender.class);
        bind(CreateUser.class);
        bind(ConfirmUser.class);
    }

    @Provides
    public MessageFactory messageFactory() {
        TwilioRestClient client = new TwilioRestClient(
            applicationProperties.getProperty("twilio.account.sid"),
            applicationProperties.getProperty("twilio.auth.token"));

        Account account = client.getAccount();
        return account.getMessageFactory();
    }
}

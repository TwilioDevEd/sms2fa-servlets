package com.twilio.sms2fa.infrastructure.guice;

import com.google.inject.Provides;
import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sms2fa.application.servlets.ConfirmationsNewServlet;
import com.twilio.sms2fa.application.servlets.UsersNewServlet;
import com.twilio.sms2fa.application.servlets.UsersServlet;
import com.twilio.sms2fa.domain.repository.UserRepository;
import com.twilio.sms2fa.domain.service.CreateUser;
import com.twilio.sms2fa.domain.service.MessageSender;
import com.twilio.sms2fa.infrastructure.repository.UserInMemoryRepository;
import com.twilio.sms2fa.infrastructure.service.TwilioMessageSender;

public class Sms2faGuiceModule extends ServletModule {

    private ApplicationProperties applicationProperties = new ApplicationProperties();

    @Override
    public void configureServlets() {
        Names.bindProperties(binder(), applicationProperties);

        serve("/users/").with(UsersServlet.class);
        serve("/users/new").with(UsersNewServlet.class);
        serve("/confirmations/new").with(ConfirmationsNewServlet.class);

        bind(UserRepository.class).to(UserInMemoryRepository.class);
        bind(MessageSender.class).to(TwilioMessageSender.class);
        bind(CreateUser.class);
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

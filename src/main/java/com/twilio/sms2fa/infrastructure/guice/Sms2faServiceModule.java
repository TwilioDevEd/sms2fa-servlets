package com.twilio.sms2fa.infrastructure.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sms2fa.domain.repository.UserRepository;
import com.twilio.sms2fa.domain.service.ConfirmUser;
import com.twilio.sms2fa.domain.service.CreateUser;
import com.twilio.sms2fa.domain.service.MessageSender;
import com.twilio.sms2fa.infrastructure.repository.UserJpaRepository;
import com.twilio.sms2fa.infrastructure.service.TwilioMessageSender;

public class Sms2faServiceModule extends AbstractModule {

    private ApplicationProperties applicationProperties =
            new ApplicationProperties();

    @Override
    protected void configure() {
        Names.bindProperties(binder(), applicationProperties);

        bind(UserRepository.class).to(UserJpaRepository.class);
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

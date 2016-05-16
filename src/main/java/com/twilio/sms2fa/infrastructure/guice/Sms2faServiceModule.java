package com.twilio.sms2fa.infrastructure.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
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

import static com.twilio.sms2fa.infrastructure.guice.ApplicationProperties
        .TWILIO_ACCOUNT_SID;
import static com.twilio.sms2fa.infrastructure.guice.ApplicationProperties
        .TWILIO_AUTH_TOKEN;

public class Sms2faServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        Names.bindProperties(binder(), new ApplicationProperties());

        bind(UserRepository.class).to(UserJpaRepository.class);
        bind(MessageSender.class).to(TwilioMessageSender.class);
        bind(CreateUser.class);
        bind(ConfirmUser.class);
    }

    @Provides
    public MessageFactory messageFactory(
            @Named(TWILIO_ACCOUNT_SID) final String twilioAccountSid,
            @Named(TWILIO_AUTH_TOKEN) final String twilioAuthToken) {
        TwilioRestClient client = new TwilioRestClient(
                twilioAccountSid, twilioAuthToken);

        Account account = client.getAccount();
        return account.getMessageFactory();
    }

}

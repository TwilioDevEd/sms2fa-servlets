package com.twilio.sms2fa.infrastructure.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.twilio.http.TwilioRestClient;
import com.twilio.sms2fa.domain.service.ConfirmUser;
import com.twilio.sms2fa.domain.service.CreateUser;
import com.twilio.sms2fa.domain.service.MessageSender;
import com.twilio.sms2fa.infrastructure.service.TwilioMessageSender;
import ru.vyarus.guice.validator.ImplicitValidationModule;

import static com.twilio.sms2fa.infrastructure.guice.ApplicationProperties
        .TWILIO_ACCOUNT_SID;
import static com.twilio.sms2fa.infrastructure.guice.ApplicationProperties
        .TWILIO_AUTH_TOKEN;

class Sms2faServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MessageSender.class).to(TwilioMessageSender.class);
        bind(CreateUser.class);
        bind(ConfirmUser.class);

        install(new ImplicitValidationModule());
    }

    @Provides
    public TwilioRestClient twilioRestClient(
            @Named(TWILIO_ACCOUNT_SID) final String twilioAccountSid,
            @Named(TWILIO_AUTH_TOKEN) final String twilioAuthToken) {
        return new TwilioRestClient
                .Builder(twilioAccountSid, twilioAuthToken)
                .build();
    }

}

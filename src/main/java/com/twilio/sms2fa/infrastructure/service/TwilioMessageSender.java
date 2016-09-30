package com.twilio.sms2fa.infrastructure.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.service.MessageSender;
import com.twilio.type.PhoneNumber;

@Singleton
public class TwilioMessageSender implements MessageSender {

    private static final String QUEUED = "queued";

    private TwilioRestClient twilioRestClient;
    private PhoneNumber fromPhoneNumber;

    @Inject
    public TwilioMessageSender(
            final TwilioRestClient twilioRestClient,
            @Named("TWILIO_PHONE_NUMBER") final String fromPhoneNumber) {
        this.twilioRestClient = twilioRestClient;
        this.fromPhoneNumber = new PhoneNumber(fromPhoneNumber);
    }

    @Override
    public final boolean sendCode(final User user) {
        final PhoneNumber to = new PhoneNumber(user.getPhoneNumber());
        final PhoneNumber from = fromPhoneNumber;
        final String body = user.getVerificationCode();

        Message message = new MessageCreator(to, from, body)
                .create(twilioRestClient);

        return QUEUED.equals(message.getStatus());
    }
}

package com.twilio.sms2fa.infrastructure.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.service.MessageSender;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

import static java.util.Arrays.asList;

@Singleton
public class TwilioMessageSender implements MessageSender {

    private static final String FROM = "From";
    private static final String TO = "To";
    private static final String BODY = "Body";
    private static final String QUEUED = "queued";

    private MessageFactory messageFactory;
    private String fromPhoneNumber;

    @Inject
    public TwilioMessageSender(
            final MessageFactory messageFactory,
            @Named("TWILIO_PHONE_NUMBER") final String fromPhoneNumber) {
        this.messageFactory = messageFactory;
        this.fromPhoneNumber = fromPhoneNumber;
    }

    @Override
    public final boolean sendCode(final User user) {
        try {
            List<NameValuePair> params = asList(
                    new BasicNameValuePair(FROM, fromPhoneNumber),
                    new BasicNameValuePair(TO, user.getPhoneNumber()),
                    new BasicNameValuePair(BODY, user.getVerificationCode())
            );
            Message sms = messageFactory.create(params);
            return QUEUED.equals(sms.getStatus());
        } catch (TwilioRestException e) {
            throw new RuntimeException("Error on message creation", e);
        }
    }
}

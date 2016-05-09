package com.twilio.sms2fa.infrastructure.service;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.service.MessageSender;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

import static java.util.Arrays.asList;

public class TwilioMessageSender implements MessageSender {

    private MessageFactory messageFactory;
    private String fromPhoneNumber;

    public TwilioMessageSender(MessageFactory messageFactory, String fromPhoneNumber) {
        this.messageFactory = messageFactory;
        this.fromPhoneNumber = fromPhoneNumber;
    }

    @Override
    public boolean sendCode(User user) {
        try {
            List<NameValuePair> params = asList(
                new BasicNameValuePair("From", fromPhoneNumber),
                new BasicNameValuePair("To", user.getPhoneNumber()),
                new BasicNameValuePair("Body", user.getVerificationCode())
            );
            Message sms = messageFactory.create(params);
            return "queued".equals(sms.getStatus());
        } catch (TwilioRestException e) {
            throw new RuntimeException("Error on message creation", e);
        }
    }
}

package com.twilio.sms2fa.domain;

import com.twilio.sdk.TwilioRestException;

public interface MessageSender {
    boolean sendCode(String phoneNumber, String code) throws TwilioRestException;
}

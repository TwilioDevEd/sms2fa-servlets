package com.twilio.sms2fa.domain.service;

import com.twilio.sms2fa.domain.model.User;

public interface MessageSender {
    boolean sendCode(User user);
}

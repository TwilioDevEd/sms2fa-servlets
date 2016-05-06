package com.twilio.sms2fa.domain.service;

public interface MessageSender {
    boolean sendCode(String phoneNumber, String code);
}

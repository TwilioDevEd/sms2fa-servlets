package com.twilio.sms2fa.domain.exception;

public class MessageSenderException extends DomainException {

    public MessageSenderException(final String message, final Exception e) {
        super(message, e);
    }
}

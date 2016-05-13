package com.twilio.sms2fa.domain.exception;

public class WrongUserPasswordException extends DomainException {

    public WrongUserPasswordException() {
        super("Wrong user/password.");
    }

}

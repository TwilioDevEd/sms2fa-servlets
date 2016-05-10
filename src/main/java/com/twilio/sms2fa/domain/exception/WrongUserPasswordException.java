package com.twilio.sms2fa.domain.exception;

public class WrongUserPasswordException extends RuntimeException {

    public WrongUserPasswordException(){
        super("Wrong user/password.");
    }

}

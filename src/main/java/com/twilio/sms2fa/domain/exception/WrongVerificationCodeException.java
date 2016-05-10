package com.twilio.sms2fa.domain.exception;

public class WrongVerificationCodeException extends RuntimeException {

    public WrongVerificationCodeException(String verificationCode){
        super(String.format("Verification code %s does not match.", verificationCode));
    }

}

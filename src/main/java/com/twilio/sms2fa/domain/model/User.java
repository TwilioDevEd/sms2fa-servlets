package com.twilio.sms2fa.domain.model;

public class User {

    private String verificationCode;
    private String phoneNumber;

    public User(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void updateVerificationCode(String verificationCode){
        this.verificationCode = verificationCode;
    }

    public String getVerificationCode(){
        return verificationCode;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
}

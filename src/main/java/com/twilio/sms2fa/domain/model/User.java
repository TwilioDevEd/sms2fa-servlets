package com.twilio.sms2fa.domain.model;

import java.util.Random;

public class User {

    private String verificationCode;
    private String phoneNumber;

    public User(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void generateVerificationCode(){
        int min = 100000;
        int max = 999999;
        Random rand = new Random();
        Integer code = rand.nextInt(max - min + 1) + min;
        this.verificationCode = code.toString();
    }

    public String getVerificationCode(){
        return verificationCode;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
}

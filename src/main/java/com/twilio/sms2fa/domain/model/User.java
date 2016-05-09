package com.twilio.sms2fa.domain.model;

import java.util.Random;

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String verificationCode;
    private String phoneNumber;

    public User(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public User(long id, String firstName, String lastName, String email, String phoneNumber, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public void generateVerificationCode(){
        int min = 100000;
        int max = 999999;
        Random rand = new Random();
        Integer code = rand.nextInt(max - min + 1) + min;
        this.verificationCode = code.toString();
    }

    public Long getId(){
        return id;
    }

    public String getVerificationCode(){
        return verificationCode;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
}

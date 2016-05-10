package com.twilio.sms2fa.domain.model;

import com.twilio.sms2fa.domain.exception.WrongVerificationCodeException;

import java.util.Random;

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String verificationCode;
    private String phoneNumber;
    private boolean confirmed;

    public User(String firstName, String lastName, String email, String phoneNumber, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.verificationCode = generateVerificationCode();
        this.confirmed = false;
    }

    public static String generateVerificationCode(){
        int min = 100000;
        int max = 999999;
        Random rand = new Random();
        Integer code = rand.nextInt(max - min + 1) + min;
        return code.toString();
    }

    public void confirm(String verificationCode) {
        if (!this.verificationCode.equals(verificationCode)){
            throw new WrongVerificationCodeException(verificationCode);
        }
        confirmed = true;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void generateNewVerificationCode() {
        this.verificationCode = generateVerificationCode();
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}

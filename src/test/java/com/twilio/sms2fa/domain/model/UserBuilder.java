package com.twilio.sms2fa.domain.model;

public class UserBuilder {

    private String firstName = "Foo";
    private String lastName = "Bar";
    private String email = "foo@bar.com";
    private String phoneNumber = "123123123123";
    private String password = "pass";
    private String verificationCode;

    public User build() {
        return new User(firstName, lastName, email, phoneNumber, password);
    }
}

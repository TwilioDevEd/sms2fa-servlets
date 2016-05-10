package com.twilio.sms2fa.domain.model;

import org.apache.commons.lang3.reflect.FieldUtils;

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

    public User buildWithVerificationCode(String verificationCode) {
        try {
            User user = build();
            FieldUtils.writeField(FieldUtils.getField(User.class, "verificationCode", true), user, verificationCode);
            return user;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

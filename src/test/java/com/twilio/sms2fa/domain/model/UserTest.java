package com.twilio.sms2fa.domain.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void shouldGenerate6DigitCode(){
        User user = new User("my-phone-number");
        user.generateVerificationCode();

        String code = user.getVerificationCode();
        String regex = "^\\d{6}$";
        String msg = String.format("code %s should match pattern %s", code, regex);

        assertTrue(msg, code.matches(regex));
    }
}

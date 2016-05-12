package com.twilio.sms2fa.domain.service;

import com.twilio.sms2fa.domain.exception.WrongVerificationCodeException;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.model.UserBuilder;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConfirmUserTest {

    @Test(expected = WrongVerificationCodeException.class)
    public void shouldThrowExceptionWhenConfirmWithWrongVerificationCode() {
        User user = new UserBuilder().buildWithVerificationCode("123123");
        user.confirm("111111");
    }

    @Test
    public void shouldBeConfirmedWhenConfirmWithWrongVerificationCode() {
        User user = new UserBuilder().buildWithVerificationCode("123123");
        user.confirm("123123");
        assertTrue(user.isConfirmed());
    }
}

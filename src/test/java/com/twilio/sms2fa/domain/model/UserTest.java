package com.twilio.sms2fa.domain.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void shouldGenerate6DigitCode(){
        String code = User.generateVerificationCode();

        String regex = "^\\d{6}$";
        String msg = String.format("code %s should match pattern %s", code, regex);

        assertTrue(msg, code.matches(regex));
    }

    @Test
    public void shouldGenerateCodeForUserOnConstructor(){
        User user = new User("foo", "bar", "foo@bar.com", "+12321321321", "pass");

        assertThat(user.getVerificationCode(), is(not(nullValue())));
    }

}

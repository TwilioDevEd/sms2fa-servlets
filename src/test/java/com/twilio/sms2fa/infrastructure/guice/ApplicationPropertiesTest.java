package com.twilio.sms2fa.infrastructure.guice;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ApplicationPropertiesTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfTwilioAccountSidIsNull() {
        new ApplicationProperties(null, "token", "number");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfTwilioAuthTokenIsNull() {
        new ApplicationProperties("sid", null, "number");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfTwilioPhoneNumberIsNull() {
        new ApplicationProperties("sid", "token", null);
    }

    @Test
    public void shouldNotThrowExceptionIfAllArgsArePresent() {
        ApplicationProperties properties =
                new ApplicationProperties("sid", "token", "phone");

        assertThat(properties.get(ApplicationProperties.TWILIO_ACCOUNT_SID),
                is("sid"));
        assertThat(properties.get(ApplicationProperties.TWILIO_AUTH_TOKEN),
                is("token"));
        assertThat(properties.get(ApplicationProperties.TWILIO_PHONE_NUMBER),
                is("phone"));


    }
}

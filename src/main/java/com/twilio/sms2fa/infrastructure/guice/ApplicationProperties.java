package com.twilio.sms2fa.infrastructure.guice;

import java.util.Properties;

class ApplicationProperties extends Properties {

    static final String TWILIO_ACCOUNT_SID = "TWILIO_ACCOUNT_SID";
    static final String TWILIO_AUTH_TOKEN = "TWILIO_AUTH_TOKEN";
    static final String TWILIO_PHONE_NUMBER = "TWILIO_PHONE_NUMBER";

    ApplicationProperties() {
        put(TWILIO_ACCOUNT_SID, System.getenv(TWILIO_ACCOUNT_SID));
        put(TWILIO_AUTH_TOKEN, System.getenv(TWILIO_AUTH_TOKEN));
        put(TWILIO_PHONE_NUMBER, System.getenv(TWILIO_PHONE_NUMBER));
    }
}

package com.twilio.sms2fa.infrastructure.guice;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties extends Properties {

    private static final String APPLICATION_PROPERTIES = "/application.properties";

    public ApplicationProperties(){
        try {
            load(ApplicationProperties.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

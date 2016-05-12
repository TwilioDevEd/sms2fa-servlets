package com.twilio.sms2fa.infrastructure.guice;

import java.io.IOException;
import java.util.Properties;

class ApplicationProperties extends Properties {

    private static final String PROPERTIES = "/application.properties";

    ApplicationProperties() {
        try {
            load(ApplicationProperties.class.getClassLoader()
                    .getResourceAsStream(PROPERTIES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

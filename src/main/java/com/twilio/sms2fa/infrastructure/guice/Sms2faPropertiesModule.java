package com.twilio.sms2fa.infrastructure.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class Sms2faPropertiesModule extends AbstractModule {
    @Override
    protected void configure() {
        Names.bindProperties(binder(), new ApplicationProperties());
    }
}

package com.twilio.sms2fa.infrastructure.guice;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.twilio.sms2fa.domain.repository.UserRepository;
import com.twilio.sms2fa.infrastructure.repository.UserJpaRepository;

public class Sms2faRepositoryModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new JpaPersistModule("jpa-sms2fa"));

        bind(UserRepository.class).to(UserJpaRepository.class);
    }
}

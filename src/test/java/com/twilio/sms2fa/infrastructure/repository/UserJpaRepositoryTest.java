package com.twilio.sms2fa.infrastructure.repository;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.model.UserBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserJpaRepositoryTest {

    private UserJpaRepository userJpaRepository;

    @Before
    public void setUp() throws ClassNotFoundException {
        Class.forName("com.twilio.sms2fa.domain.model.User");
        Class.forName("com.twilio.sms2fa.infrastructure.repository.UserJpaRepository");

        JpaPersistModule testPersistModule = new JpaPersistModule("jpa-sms2fa-test");
        Injector injector = Guice.createInjector(testPersistModule);
        PersistService instance = injector.getInstance(PersistService.class);
        instance.start();
        userJpaRepository = injector.getInstance(UserJpaRepository.class);
    }

    @Test
    public void shouldSaveUser() {
        User user = new UserBuilder().build();
        user = userJpaRepository.save(user);

        assertThat(user.getId(), is(1L));
    }

    @Test
    public void shouldFindUserById() {
        User user = new UserBuilder().build();
        user = userJpaRepository.save(user);

        User userFound = userJpaRepository.findById(user.getId());

        assertThat(userFound, is(user));
    }

    @Ignore("to be fixed asap")
    @Test
    public void shouldFindUserByEmail() {
        User user = new UserBuilder().withEmail("foo@bar.com").build();
        user = userJpaRepository.save(user);

        User userFound = userJpaRepository.findByEmail("foo@bar.com").get();

        assertThat(userFound, is(user));
    }


}
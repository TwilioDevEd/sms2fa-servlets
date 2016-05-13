package com.twilio.sms2fa.infrastructure.repository;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.model.UserBuilder;
import com.twilio.sms2fa.helper.IntegrationTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.PersistenceException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class UserJpaRepositoryTest {

    private UserJpaRepository userJpaRepository;
    private IntegrationTestHelper integrationTestHelper;

    @Before
    public void setUp() throws ClassNotFoundException {
        JpaPersistModule testPersistModule =
                new JpaPersistModule("jpa-sms2fa-test");
        Injector injector = Guice.createInjector(testPersistModule);
        PersistService instance = injector.getInstance(PersistService.class);
        instance.start();
        userJpaRepository = injector.getInstance(UserJpaRepository.class);
        integrationTestHelper = injector
                .getInstance(IntegrationTestHelper.class);
        integrationTestHelper.cleanTable(User.class);
        integrationTestHelper.startTransaction();
    }

    @After
    public void after() {
        integrationTestHelper.rollBackTransaction();
    }

    @Test
    public void shouldCreateNewUser() {
        User user = new UserBuilder().build();
        user = userJpaRepository.save(user);

        assertThat(user.getId(), is(not(nullValue())));
    }

    @Test
    public void shouldUpdateExistentUser() {
        User user = userJpaRepository.save(new UserBuilder().build());
        String oldCode = user.getVerificationCode();

        user.generateNewVerificationCode();
        userJpaRepository.save(user);

        User userFound = userJpaRepository.findById(user.getId());
        String newCode = userFound.getVerificationCode();
        assertThat(oldCode, is(not(newCode)));
    }

    @Test
    public void shouldFindUserById() {
        User user = new UserBuilder().build();

        user = userJpaRepository.save(user);

        User userFound = userJpaRepository.findById(user.getId());

        assertThat(userFound, is(user));
    }

    @Test(expected = PersistenceException.class)
    public void shouldNotAllowTwoUsersWithSaveEmail(){
        User user1 = new UserBuilder().withEmail("foooo@bar.com").build();
        User user2 = new UserBuilder().withEmail("foooo@bar.com").build();
        userJpaRepository.save(user1);
        userJpaRepository.save(user2);
    }

    @Test
    public void shouldFindUserByEmail() {
        User user = new UserBuilder().withEmail("foo2@bar.com").build();
        user = userJpaRepository.save(user);

        User userFound = userJpaRepository.findByEmail("foo2@bar.com").get();

        assertThat(userFound.getId(), is(user.getId()));
    }

}

package com.twilio.sms2fa.domain.service;

import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.model.UserBuilder;
import com.twilio.sms2fa.domain.repository.UserRepository;
import com.twilio.sms2fa.infrastructure.repository.UserInMemoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreateUserTest {

    private CreateUser createUser;

    private UserRepository userRepository;
    private User user;
    @Mock
    private MessageSender messageSender;

    @Before
    public void setUp() {
        initMocks(this);

        this.userRepository = new UserInMemoryRepository();
        this.createUser = new CreateUser(userRepository, messageSender);

        this.user = new UserBuilder().build();
    }

    @Test
    public void shouldSendMessage() {
        createUser.create(user);

        verify(messageSender, only()).sendCode(user);
    }

    @Test
    public void shouldSaveUser() {
        createUser.create(user);

        assertThat(userRepository.findById(1), is(user));
    }

}

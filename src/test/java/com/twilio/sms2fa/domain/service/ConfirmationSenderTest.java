package com.twilio.sms2fa.domain.service;

import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.spy;

public class ConfirmationSenderTest {

    private ConfirmationSender confirmationSender;
    @Mock
    private MessageSender messageSender;
    @Mock
    private UserRepository userRepository;
    private User user;

    @Before
    public void setUp(){
        initMocks(this);
        this.confirmationSender = new ConfirmationSender(messageSender, userRepository);
        this.user = new User("my-phone-number");
    }

    @Test
    public void shouldGenerateCodeForUser(){
        User userSpy = spy(user);

        confirmationSender.sendConfirmationTo(userSpy);

        verify(userSpy).generateVerificationCode();
    }

    @Test
    public void shouldSendMessage(){
        confirmationSender.sendConfirmationTo(user);

        verify(messageSender, only()).sendCode(eq(user.getPhoneNumber()), anyString());
    }

    @Test
    public void shouldUpdateUser(){
        confirmationSender.sendConfirmationTo(user);

        verify(userRepository, only()).update(user);
    }

}
package com.twilio.sms2fa.domain.service;

import com.twilio.sms2fa.domain.exception.WrongUserPasswordException;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.model.UserBuilder;
import com.twilio.sms2fa.domain.repository.UserRepository;
import com.twilio.sms2fa.infrastructure.repository.UserInMemoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class LogInTest {

    @Mock
    private MessageSender messageSender;

    private UserRepository userRepository;

    private LogIn logIn;

    @Before
    public void setUp() {
        initMocks(this);

        userRepository = new UserInMemoryRepository();

        logIn = new LogIn(userRepository, messageSender);

        userRepository.save(new UserBuilder().withEmail("login@bar.com").withPass("1234").build());
        userRepository.save(new UserBuilder().withEmail("test@bar.com").withPass("111").build());
        userRepository.save(new UserBuilder().withEmail("foo@bar.com").withPass("abcd").build());
    }

    @Test(expected = WrongUserPasswordException.class)
    public void shouldThrowExceptionWhenPasswordIsWrong() {
        logIn.authenticate("login@bar.com", "1");
    }

    @Test(expected = WrongUserPasswordException.class)
    public void shouldThrowExceptionWhenEmailDoesNotExist() {
        logIn.authenticate("crazy@bar.com", "1234");
    }

    @Test
    public void shouldGenerateAndSaveUserWhenPassIsCorrect() {
        User user = userRepository.findById(1L);
        String verificationCode = user.getVerificationCode();

        logIn.authenticate("login@bar.com", "1234");

        assertThat(verificationCode, is(not(userRepository.findById(1L).getVerificationCode())));
    }

    @Test
    public void shouldSendMessageWhenPassIsCorrect() {
        User user = userRepository.findById(1L);

        logIn.authenticate("login@bar.com", "1234");

        verify(messageSender, times(1)).sendCode(user);
    }
}

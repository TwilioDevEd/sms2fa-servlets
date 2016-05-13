package com.twilio.sms2fa.domain.service;

import com.twilio.sdk.TwilioRestException;
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
    private User firstUser;
    private User secondUser;
    private User thirdUser;

    @Before
    public void setUp() {
        initMocks(this);

        userRepository = new UserInMemoryRepository();

        logIn = new LogIn(userRepository, messageSender);

        firstUser = userRepository.save(new UserBuilder()
                .withEmail("login@bar.com")
                .withPass("1234").build());
        secondUser = userRepository.save(new UserBuilder()
                .withEmail("test@bar.com")
                .withPass("111").build());
        thirdUser = userRepository.save(new UserBuilder()
                .withEmail("foo@bar.com")
                .withPass("abcd").build());
    }

    @Test(expected = WrongUserPasswordException.class)
    public void shouldThrowExceptionWhenPasswordIsWrong()
            throws TwilioRestException {
        logIn.authenticate("login@bar.com", "1");
    }

    @Test(expected = WrongUserPasswordException.class)
    public void shouldThrowExceptionWhenEmailDoesNotExist()
            throws TwilioRestException {
        logIn.authenticate("crazy@bar.com", "1234");
    }

    @Test
    public void shouldGenerateAndSaveUserWhenPassIsCorrect()
            throws TwilioRestException {
        User user = userRepository.findById(firstUser.getId());
        String verificationCode = user.getVerificationCode();

        logIn.authenticate("login@bar.com", "1234");

        assertThat(verificationCode,
                is(not(userRepository.findById(firstUser.getId())
                        .getVerificationCode())));
    }

    @Test
    public void shouldSendMessageWhenPassIsCorrect()
            throws TwilioRestException {
        User user = userRepository.findById(firstUser.getId());

        logIn.authenticate("login@bar.com", "1234");

        verify(messageSender, times(1)).sendCode(user);
    }
}

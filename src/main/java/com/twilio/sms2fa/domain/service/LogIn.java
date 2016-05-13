package com.twilio.sms2fa.domain.service;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sms2fa.domain.exception.WrongUserPasswordException;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

import java.util.Optional;

public class LogIn {

    private UserRepository userRepository;
    private MessageSender messageSender;

    @Inject
    public LogIn(
            final UserRepository userRepository,
            final MessageSender messageSender) {
        this.userRepository = userRepository;
        this.messageSender = messageSender;
    }

    @Transactional
    public User authenticate(final String email, final String password)
            throws TwilioRestException {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user1 = userOpt.get();
            if (user1.authenticate(password)) {
                user1.generateNewVerificationCode();
                userRepository.save(user1);
                messageSender.sendCode(user1);
            } else {
                throw new WrongUserPasswordException();
            }
        }
        userOpt.orElseThrow(WrongUserPasswordException::new);
        return userOpt.get();
    }
}

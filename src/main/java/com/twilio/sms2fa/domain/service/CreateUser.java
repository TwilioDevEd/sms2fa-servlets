package com.twilio.sms2fa.domain.service;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

import javax.validation.Valid;

public class CreateUser {

    private UserRepository userRepository;
    private MessageSender messageSender;

    @Inject
    public CreateUser(
            final UserRepository userRepository,
            final MessageSender messageSender) {
        this.userRepository = userRepository;
        this.messageSender = messageSender;
    }

    @Transactional
    public User create(@Valid final User user) {
        User savedUser = userRepository.save(user);
        messageSender.sendCode(savedUser);
        return savedUser;
    }
}

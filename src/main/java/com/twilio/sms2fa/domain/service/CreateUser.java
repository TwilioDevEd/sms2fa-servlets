package com.twilio.sms2fa.domain.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

import javax.validation.Valid;

@Singleton
public class CreateUser {

    private UserRepository userRepository;
    private MessageSender messageSender;

    @Inject
    public CreateUser(UserRepository userRepository, MessageSender messageSender) {
        this.userRepository = userRepository;
        this.messageSender = messageSender;
    }

    public User create(@Valid User user){
        User savedUser = userRepository.save(user);
        messageSender.sendCode(savedUser);
        return savedUser;
    }
}

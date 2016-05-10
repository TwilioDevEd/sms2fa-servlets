package com.twilio.sms2fa.domain.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

@Singleton
public class CreateUser {

    private UserRepository userRepository;
    private MessageSender messageSender;

    @Inject
    public CreateUser(UserRepository userRepository, MessageSender messageSender) {
        this.userRepository = userRepository;
        this.messageSender = messageSender;
    }

    public User create(User user){
        User savedUser = userRepository.save(user);
        messageSender.sendCode(savedUser);
        return savedUser;
    }
}

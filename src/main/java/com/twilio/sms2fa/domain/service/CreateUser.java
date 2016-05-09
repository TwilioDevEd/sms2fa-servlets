package com.twilio.sms2fa.domain.service;

import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

public class CreateUser {

    private UserRepository userRepository;
    private MessageSender messageSender;

    public CreateUser(UserRepository userRepository, MessageSender messageSender) {
        this.userRepository = userRepository;
        this.messageSender = messageSender;
    }

    public void create(User user){
        userRepository.save(user);
        messageSender.sendCode(user);
    }
}

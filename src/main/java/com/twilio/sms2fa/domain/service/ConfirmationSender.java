package com.twilio.sms2fa.domain.service;

import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;


public class ConfirmationSender {

    private MessageSender messageSender;
    private UserRepository userRepository;

    public ConfirmationSender(MessageSender messageSender, UserRepository userRepository) {
        this.messageSender = messageSender;
        this.userRepository = userRepository;
    }


    public boolean sendConfirmationTo(User user){
        user.generateVerificationCode();
        userRepository.update(user);
        return messageSender.sendCode(user.getPhoneNumber(), user.getVerificationCode());
    }
}

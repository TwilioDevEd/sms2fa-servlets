package com.twilio.sms2fa.domain.service;

import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

import static com.twilio.sms2fa.domain.service.CodeGenerator.generateConfirmationCode;


public class ConfirmationSender {

    private MessageSender messageSender;
    private UserRepository userRepository;

    public ConfirmationSender(MessageSender messageSender, UserRepository userRepository) {
        this.messageSender = messageSender;
        this.userRepository = userRepository;
    }


    public boolean sendConfirmationTo(User user){
        user.updateVerificationCode(generateConfirmationCode());
        userRepository.update(user);
        return messageSender.sendCode(user.getPhoneNumber(), user.getVerificationCode());
    }
}

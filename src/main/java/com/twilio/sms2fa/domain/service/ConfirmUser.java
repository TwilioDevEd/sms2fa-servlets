package com.twilio.sms2fa.domain.service;

import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

public class ConfirmUser {

    private UserRepository userRepository;

    public ConfirmUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void confirm(User user, String verificationCode){
        user.confirm(verificationCode);
        userRepository.save(user);
    }
}

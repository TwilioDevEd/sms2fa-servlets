package com.twilio.sms2fa.infrastructure.repository;

import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class UserInMemoryRepository implements UserRepository {

    private Map<Long, User> users = new HashMap<>();
    private long sequence = 0;

    @Override
    public User create(String firstName, String lastName, String email, String phoneNumber, String password) {
        return new User(nextSequence(), firstName, lastName, email, phoneNumber, password);
    }

    @Override
    public User update(User user) {
        return null;
    }

    public long nextSequence(){
        return sequence++;
    }
}

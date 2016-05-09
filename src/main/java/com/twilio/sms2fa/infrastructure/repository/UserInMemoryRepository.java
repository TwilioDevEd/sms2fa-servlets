package com.twilio.sms2fa.infrastructure.repository;

import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class UserInMemoryRepository implements UserRepository {

    private Map<Long, User> users = new HashMap<>();
    private long sequence = 0;

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(nextSequence());
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(long id) {
        return users.get(id);
    }

    public long nextSequence(){
        return ++sequence;
    }
}

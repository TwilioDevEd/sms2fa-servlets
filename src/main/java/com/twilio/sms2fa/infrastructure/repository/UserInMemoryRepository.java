package com.twilio.sms2fa.infrastructure.repository;

import com.google.inject.Singleton;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
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

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public long nextSequence(){
        return ++sequence;
    }
}

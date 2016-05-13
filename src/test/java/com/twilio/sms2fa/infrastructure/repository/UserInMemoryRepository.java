package com.twilio.sms2fa.infrastructure.repository;

import com.google.inject.Singleton;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public class UserInMemoryRepository implements UserRepository {

    private Map<String, User> users = new HashMap<>();

    @Override
    public User save(final User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(final String id) {
        return users.get(id);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

}

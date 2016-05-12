package com.twilio.sms2fa.infrastructure.repository;

import com.google.inject.Singleton;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
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
            setId(user, nextSequence());
        }
        users.put(user.getId(), user);
        return user;
    }

    private void setId(User user, long id) {
        try {
            Field idField = FieldUtils.getField(User.class, "id", true);
            FieldUtils.writeField(idField, user, id);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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

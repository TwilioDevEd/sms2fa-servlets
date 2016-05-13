package com.twilio.sms2fa.domain.repository;

import com.twilio.sms2fa.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    User findById(String id);

    Optional<User> findByEmail(String email);
}

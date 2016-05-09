package com.twilio.sms2fa.domain.repository;

import com.twilio.sms2fa.domain.model.User;

public interface UserRepository {

    User update(User user);

    User create(String firstName, String lastName, String email, String phoneNumber, String password);
}

package com.twilio.sms2fa.domain.repository;

import com.twilio.sms2fa.domain.model.User;

public interface UserRepository {

    void update(User user);

}

package com.twilio.sms2fa.infrastructure.repository;

import com.google.inject.Singleton;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

@Singleton
public class UserJpaRepository implements UserRepository {

    private EntityManager entityManager;

    @Inject
    public UserJpaRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(final User user) {
        return entityManager.merge(user);
    }

    @Override
    public User findById(final String id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.email = :email")
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }
}

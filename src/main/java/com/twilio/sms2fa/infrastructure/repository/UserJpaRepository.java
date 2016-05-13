package com.twilio.sms2fa.infrastructure.repository;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;

@Singleton
public class UserJpaRepository implements UserRepository {

    private Provider<EntityManager> entityManagerProvider;

    @Inject
    public UserJpaRepository(final Provider<EntityManager> provider) {
        this.entityManagerProvider = provider;
    }

    @Override
    public User save(final User user) {
        EntityManager entityManager = entityManagerProvider.get();
        User merge = entityManager.merge(user);
        entityManager.flush();
        return merge;
    }

    @Override
    public User findById(final String id) {
        EntityManager entityManager = entityManagerProvider.get();
        return entityManager.find(User.class, id);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        EntityManager entityManager = entityManagerProvider.get();
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.email = :email")
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }
}

package com.twilio.sms2fa.helper;

import com.google.inject.Inject;

import javax.persistence.EntityManager;

public class IntegrationTestHelper {

    private EntityManager entityManager;

    @Inject
    public IntegrationTestHelper(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void finishTransaction() {
        this.entityManager.getTransaction().commit();
    }

    public void startTransaction() {
        this.entityManager.getTransaction().begin();
    }

    public <T> void cleanTable(final Class<T> clazz) {
        this.entityManager.getTransaction().begin();
        String deleteStatement = String.format("delete from %s", clazz.getSimpleName());
        this.entityManager.createQuery(deleteStatement).executeUpdate();
        this.entityManager.getTransaction().commit();
    }

}

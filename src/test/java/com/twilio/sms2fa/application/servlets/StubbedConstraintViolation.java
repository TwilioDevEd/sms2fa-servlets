package com.twilio.sms2fa.application.servlets;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

public class StubbedConstraintViolation<T> implements ConstraintViolation<T> {

    private String message;

    public StubbedConstraintViolation(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getMessageTemplate() {
        return null;
    }

    @Override
    public T getRootBean() {
        return null;
    }

    @Override
    public Class<T> getRootBeanClass() {
        return null;
    }

    @Override
    public Object getLeafBean() {
        return null;
    }

    @Override
    public Object[] getExecutableParameters() {
        return new Object[0];
    }

    @Override
    public Object getExecutableReturnValue() {
        return null;
    }

    @Override
    public Path getPropertyPath() {
        return null;
    }

    @Override
    public Object getInvalidValue() {
        return null;
    }

    @Override
    public ConstraintDescriptor<?> getConstraintDescriptor() {
        return null;
    }

    @Override
    public <U> U unwrap(final Class<U> type) {
        return null;
    }
}

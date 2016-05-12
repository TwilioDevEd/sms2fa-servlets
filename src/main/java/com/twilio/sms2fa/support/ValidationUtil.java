package com.twilio.sms2fa.support;

import javax.validation.ConstraintViolationException;

import static java.lang.String.format;

public class ValidationUtil {
    public static String extractMessage(ConstraintViolationException e) {
        return format("<ul>%s</ul>",
                    e.getConstraintViolations().stream()
                    .map(constraintViolation -> format("<li>%s</li>", constraintViolation.getMessage()))
                    .reduce((s, s2) -> s+s2)
                    .get());
    }
}

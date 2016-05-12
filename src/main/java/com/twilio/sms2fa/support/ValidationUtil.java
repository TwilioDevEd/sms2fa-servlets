package com.twilio.sms2fa.support;

import javax.validation.ConstraintViolationException;

import static java.lang.String.format;

public final class ValidationUtil {

    private ValidationUtil() {
    }

    public static String extractMessage(final ConstraintViolationException e) {
        return format("<ul>%s</ul>",
                e.getConstraintViolations().stream()
                        .map(cv -> format("<li>%s</li>", cv.getMessage()))
                        .reduce((s, s2) -> s + s2)
                        .get());
    }
}

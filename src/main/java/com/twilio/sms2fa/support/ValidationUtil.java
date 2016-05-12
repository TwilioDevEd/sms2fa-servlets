package com.twilio.sms2fa.support;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class ValidationUtil {
    public static String extractMessage(ConstraintViolationException e) {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>");
        Set<ConstraintViolation<?>> cvs = e.getConstraintViolations();
        for (ConstraintViolation<?> cv : cvs) {
            sb.append("<li>").append(cv.getMessage()).append("</li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }
}

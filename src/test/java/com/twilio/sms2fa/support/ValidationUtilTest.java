package com.twilio.sms2fa.support;

import com.twilio.sms2fa.application.servlets.StubbedConstraintViolation;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedHashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ValidationUtilTest {

    @Test
    public void shouldFormatMessageWithHtmlUlLiTags() {
        String firstNameMessage = "First Name may not be blank";
        String lastNameMessage = "Last Name may not be blank";
        LinkedHashSet<ConstraintViolation<?>> constraintViolations = new LinkedHashSet<>();
        constraintViolations.add(new StubbedConstraintViolation(firstNameMessage));
        constraintViolations.add(new StubbedConstraintViolation(lastNameMessage));
        ConstraintViolationException constraintViolationException = new ConstraintViolationException(constraintViolations);

        String message = ValidationUtil.extractMessage(constraintViolationException);

        assertThat(message, is("<ul><li>" + lastNameMessage + "</li><li>" + firstNameMessage + "</li></ul>"));
    }
}

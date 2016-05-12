package com.twilio.sms2fa.support;

import com.twilio.sms2fa.application.servlets.StubbedConstraintViolation;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedHashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;

public class ValidationUtilTest {

    @Test
    public void shouldFormatMessageWithHtmlUlLiTags() {
        String firstNameMessage = "First Name may not be blank";
        String lastNameMessage = "Last Name may not be blank";
        LinkedHashSet<ConstraintViolation<?>> constraintViolations =
                new LinkedHashSet<>();
        constraintViolations.add(
                new StubbedConstraintViolation(firstNameMessage));
        constraintViolations.add(
                new StubbedConstraintViolation(lastNameMessage));
        ConstraintViolationException exception =
                new ConstraintViolationException(constraintViolations);

        String message = ValidationUtil.extractMessage(exception);

        assertThat(message, containsString(firstNameMessage));
        assertThat(message, containsString(lastNameMessage));

        assertTrue(message.matches("<ul><li>.+</li><li>.+</li></ul>"));
    }
}

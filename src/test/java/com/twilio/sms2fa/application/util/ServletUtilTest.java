package com.twilio.sms2fa.application.util;

import com.twilio.sms2fa.application.servlets.StubbedConstraintViolation;
import com.twilio.sms2fa.domain.exception.WrongUserPasswordException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.LinkedHashSet;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

public class ServletUtilTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() {
        initMocks(this);
        when(request.getRequestDispatcher("index.jsp")).thenReturn(
                requestDispatcher);
    }

    @Test
    public void shouldHandlePersistenceExceptionFromEmailConstraint() throws
            ServletException,
            IOException {
        ServletUtil.handleException(new PersistenceException(
                "UNIQUE constraint failed: USER.EMAIL"), request, response,
                "index.jsp");

        verify(request).setAttribute("errorMessage",
                "User with this e-mail already exists");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void shouldHandleGenericPersistenceException() throws
            ServletException, IOException {
        ServletUtil.handleException(new PersistenceException(
                        "SomethingElse"), request, response,
                "index.jsp");

        verify(request).setAttribute("errorMessage",
                "SomethingElse");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void shouldHandleDomainException() throws
            ServletException, IOException {
        WrongUserPasswordException e = new WrongUserPasswordException();
        ServletUtil.handleException(e, request, response, "index.jsp");

        verify(request).setAttribute("errorMessage", e.getMessage());
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void shouldHandleConstraintViolationException() throws
            ServletException, IOException {
        LinkedHashSet<ConstraintViolation<?>> constraintViolations =
                new LinkedHashSet<>();
        constraintViolations.add(
                new StubbedConstraintViolation("First name may not be blank"));
        ConstraintViolationException cve =
                new ConstraintViolationException(constraintViolations);

        ServletUtil.handleException(cve, request, response, "index.jsp");

        verify(request).setAttribute("errorMessage",
                "<ul><li>First name may not be blank</li></ul>");
        verify(requestDispatcher).forward(request, response);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotHandleGenericException() throws
            ServletException, IOException {
        RuntimeException re = new NullPointerException("Random Error");
        ServletUtil.handleException(re, request, response, "index.jsp");
    }
}

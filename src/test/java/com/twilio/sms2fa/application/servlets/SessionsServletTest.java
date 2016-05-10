package com.twilio.sms2fa.application.servlets;

import com.twilio.sms2fa.domain.exception.WrongUserPasswordException;
import com.twilio.sms2fa.domain.service.LogIn;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

public class SessionsServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private LogIn logIn;

    private SessionsServlet servlet;

    @Before
    public void setUp(){
        initMocks(this);
        servlet = new SessionsServlet(logIn);
    }

    @Test
    public void shouldForwardToSessionsNewJspWhenLogInThrowsException() throws ServletException, IOException {
        when(request.getRequestDispatcher("/WEB-INF/pages/sessions/new.jsp"))
                .thenReturn(requestDispatcher);

        doThrow(new WrongUserPasswordException()).when(logIn).authenticate("foo", "bar");
        when(request.getParameter("email")).thenReturn("foo");
        when(request.getParameter("password")).thenReturn("bar");

        servlet.doPost(request, response);

        verify(request, times(1)).setAttribute("errorMessage", new WrongUserPasswordException().getMessage());
        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
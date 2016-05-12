package com.twilio.sms2fa.application.servlets;

import com.twilio.sms2fa.application.constants.InternalResource;
import com.twilio.sms2fa.domain.exception.WrongVerificationCodeException;
import com.twilio.sms2fa.domain.model.User;
import com.twilio.sms2fa.domain.model.UserBuilder;
import com.twilio.sms2fa.domain.service.ConfirmUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

public class ConfirmationsServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private ConfirmUser confirmUser;

    private ConfirmationsServlet servlet;
    private User userInSession;

    @Before
    public void setUp() {
        initMocks(this);
        when(request.getSession()).thenReturn(session);
        userInSession = new UserBuilder().build();
        when(session.getAttribute("user")).thenReturn(userInSession);

        servlet = new ConfirmationsServlet(confirmUser);
    }

    @Test
    public void shouldForwardToConfirmationsNewJspWhenItThrowsWrongVerificationCodeException() throws ServletException, IOException {
        when(request.getRequestDispatcher(InternalResource.CONFIRMATIONS_NEW_JSP.getPath()))
                .thenReturn(requestDispatcher);
        when(request.getParameter("verification_code")).thenReturn("123");
        WrongVerificationCodeException ex = new WrongVerificationCodeException(userInSession.getVerificationCode());

        doThrow(ex).when(confirmUser).confirm(userInSession, "123");

        servlet.doPost(request, response);
        verify(request, times(1)).setAttribute("errorMessage", ex.getMessage());
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    public void shouldRedirectToSecretsWhenItConfirms() throws ServletException, IOException {
        when(request.getParameter("verification_code")).thenReturn(userInSession.getVerificationCode());

        servlet.doPost(request, response);
        verify(response, times(1)).sendRedirect("/secrets/");
        verify(session, times(1)).setAttribute("authenticated", true);
    }
}

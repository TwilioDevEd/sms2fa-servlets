package com.twilio.sms2fa.application.servlets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

public class ConfirmationsNewServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    private ConfirmationsNewServlet servlet;

    @Before
    public void setUp(){
        initMocks(this);
        servlet = new ConfirmationsNewServlet();
    }

    @Test
    public void shouldForwardToSessionsNewJsp() throws ServletException, IOException {
        when(request.getRequestDispatcher(ConfirmationsNewServlet.WEB_INF_PAGES_CONFIRMATIONS_NEW_JSP))
                .thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
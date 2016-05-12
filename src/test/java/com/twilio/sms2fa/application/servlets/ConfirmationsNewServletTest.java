package com.twilio.sms2fa.application.servlets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.twilio.sms2fa.application.constants.InternalResource
        .CONFIRMATIONS_NEW_JSP;
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
    public void setUp() {
        initMocks(this);
        servlet = new ConfirmationsNewServlet();
    }

    @Test
    public void shouldForwardToSessionsNewJsp() throws Exception {
        when(request.getRequestDispatcher(CONFIRMATIONS_NEW_JSP.getPath()))
                .thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(requestDispatcher, times(1)).forward(request, response);
    }
}

package com.twilio.sms2fa.application.servlets;

import com.twilio.sms2fa.application.constants.InternalResource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

public class SessionsNewServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    private SessionsNewServlet sessionsNewServlet;

    @Before
    public void setUp() {
        initMocks(this);
        sessionsNewServlet = new SessionsNewServlet();
    }

    @Test
    public void shouldForwardToSessionsNewJsp() throws Exception {
        when(request.getRequestDispatcher(InternalResource
                .SESSIONS_NEW_JSP.getPath()))
                .thenReturn(requestDispatcher);

        sessionsNewServlet.doGet(request, response);

        verify(requestDispatcher, times(1)).forward(request, response);
    }
}

package com.twilio.sms2fa.application.servlets;

import com.twilio.sms2fa.application.constants.InternalResource;
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

public class SecretsServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    private SecretsServlet secretsServlet;

    @Before
    public void setUp(){
        initMocks(this);
        secretsServlet = new SecretsServlet();
    }

    @Test
    public void shouldForwardToSessionsNewJsp() throws ServletException, IOException {
        when(request.getRequestDispatcher(InternalResource.SECRETS_INDEX_JSP.getPath()))
                .thenReturn(requestDispatcher);

        secretsServlet.doGet(request, response);

        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
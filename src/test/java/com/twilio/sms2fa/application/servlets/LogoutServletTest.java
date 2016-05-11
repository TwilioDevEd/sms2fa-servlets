package com.twilio.sms2fa.application.servlets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

public class LogoutServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private HttpSession session;

    private LogoutServlet logoutServlet;

    @Before
    public void setUp(){
        initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/pages/sessions/new.jsp")).thenReturn(requestDispatcher);
        logoutServlet = new LogoutServlet();
    }

    @Test
    public void shouldInvalidateSessionWhenLogoutIsCalled() throws ServletException, IOException {
        logoutServlet.doGet(request, response);

        verify(session).invalidate();
    }

    @Test
    public void shouldForwardToSessionsNewWhenLogoutIsCalled() throws ServletException, IOException {
        logoutServlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void shouldAddMessageWhenLogoutIsCalled() throws ServletException, IOException {
        logoutServlet.doGet(request, response);

        verify(request).setAttribute("noticeMessage", "See you soon!");
    }

}
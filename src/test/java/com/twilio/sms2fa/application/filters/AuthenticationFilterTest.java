package com.twilio.sms2fa.application.filters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.when;

public class AuthenticationFilterTest {

    private AuthenticationFilter filter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Before
    public void setUp() {
        initMocks(this);
        when(request.getSession()).thenReturn(session);
        filter = new AuthenticationFilter();
    }

    @Test
    public void shouldRedirectIfItIsNotAuthenticated() throws Exception {
        filter.doFilter(request, response, filterChain);

        verify(filterChain, times(0)).doFilter(request, response);
        verify(response, times(1)).sendRedirect("/sessions/new/");
    }

    @Test
    public void shouldContinueChainIfItIsAuthenticated() throws Exception {
        when(session.getAttribute("authenticated")).thenReturn(true);
        filter.doFilter(request, response, filterChain);

        verify(response, times(0)).sendRedirect("/sessions/new/");
        verify(filterChain, times(1)).doFilter(request, response);
    }
}

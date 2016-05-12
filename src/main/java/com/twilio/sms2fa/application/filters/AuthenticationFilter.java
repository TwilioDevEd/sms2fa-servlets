package com.twilio.sms2fa.application.filters;

import com.google.inject.Singleton;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.twilio.sms2fa.application.constants.ExternalResource
        .SESSIONS_NEW;

@Singleton
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
            throws IOException, ServletException {
        if (!isAuthenticated((HttpServletRequest) request)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(SESSIONS_NEW.getPath());
        } else {
            chain.doFilter(request, response);
        }
    }

    public boolean isAuthenticated(final HttpServletRequest request) {
        return Boolean.TRUE.equals(request.getSession()
                .getAttribute("authenticated"));
    }

    @Override
    public void init(final FilterConfig filterConfig)
        throws ServletException { }

    @Override
    public void destroy() { }

}

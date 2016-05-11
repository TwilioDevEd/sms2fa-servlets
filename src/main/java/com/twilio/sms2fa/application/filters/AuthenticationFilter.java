package com.twilio.sms2fa.application.filters;

import com.google.inject.Singleton;
import com.twilio.sms2fa.application.constants.ExternalResource;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!isAuthenticated((HttpServletRequest) request)) {
            ((HttpServletResponse) response).sendRedirect(ExternalResource.SESSIONS_NEW.getPath());
        }

        chain.doFilter(request, response);
    }

    public boolean isAuthenticated(HttpServletRequest request){
        return Boolean.TRUE.equals(request.getSession().getAttribute("authenticated"));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}

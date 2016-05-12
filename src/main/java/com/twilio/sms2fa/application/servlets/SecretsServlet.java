package com.twilio.sms2fa.application.servlets;

import com.google.inject.Singleton;
import com.twilio.sms2fa.application.constants.InternalResource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class SecretsServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(InternalResource.SECRETS_INDEX_JSP.getPath()).forward(request, response);
    }

}

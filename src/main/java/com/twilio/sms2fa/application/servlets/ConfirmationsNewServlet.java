package com.twilio.sms2fa.application.servlets;

import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.twilio.sms2fa.application.constants.InternalResource
        .CONFIRMATIONS_NEW_JSP;

@Singleton
public class ConfirmationsNewServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(CONFIRMATIONS_NEW_JSP.getPath())
                .forward(request, response);
    }

}

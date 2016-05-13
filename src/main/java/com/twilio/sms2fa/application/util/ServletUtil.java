package com.twilio.sms2fa.application.util;

import com.twilio.sdk.TwilioRestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

import static com.twilio.sms2fa.application.util.ValidationUtil.extractMessage;
import static java.lang.String.format;

public final class ServletUtil {

    private ServletUtil() { }

    public static void handleException(
            final ConstraintViolationException e,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final String forwardToJsp) throws ServletException, IOException {
        handle(request, response, forwardToJsp, extractMessage(e));
    }

    public static void handleException(
            final TwilioRestException e,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final String forwardToJsp) throws ServletException, IOException {
        handle(request, response, forwardToJsp,
                format("%s: %s", e.getErrorCode(), e.getErrorMessage()));
    }

    public static void handleException(
            final Exception e,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final String forwardToJsp) throws ServletException, IOException {
        handle(request, response, forwardToJsp, e.getMessage());
    }

    private static void handle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final String forwardToJsp,
            final String message)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", message);
        request.getRequestDispatcher(forwardToJsp).forward(request, response);
    }

}

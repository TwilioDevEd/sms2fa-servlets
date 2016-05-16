package com.twilio.sms2fa.application.util;

import com.twilio.sms2fa.domain.exception.DomainException;
import org.slf4j.Logger;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

import static com.twilio.sms2fa.application.util.ValidationUtil.extractMessage;
import static org.slf4j.LoggerFactory.getLogger;

public final class ServletUtil {

    private static final Logger LOG = getLogger(ServletUtil.class);

    private ServletUtil() { }

    public static void handleException(
            final Exception e,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final String forwardToJsp) throws ServletException, IOException {
        if (e instanceof DomainException) {
            DomainException de = (DomainException) e;
            handle(request, response, forwardToJsp, de.getMessage());
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) e;
            handle(request, response, forwardToJsp, extractMessage(cve));
        } else if (e instanceof PersistenceException) {
            PersistenceException pe = (PersistenceException) e;
            if (pe.getMessage().contains(
                    "UNIQUE constraint failed: USER.EMAIL")) {
                handle(request, response, forwardToJsp,
                        "User with this e-mail already exists");
            } else {
                handle(request, response, forwardToJsp, e.getMessage());
            }
        } else {
            LOG.error("Cannot handle this exception: %s", e.getMessage(), e);
            throw new RuntimeException(e);
        }
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

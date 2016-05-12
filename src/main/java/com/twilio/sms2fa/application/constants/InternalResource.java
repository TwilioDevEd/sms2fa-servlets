package com.twilio.sms2fa.application.constants;

public enum InternalResource {

    CONFIRMATIONS_NEW_JSP("/WEB-INF/pages/confirmations/new.jsp"),
    SESSIONS_NEW_JSP("/WEB-INF/pages/sessions/new.jsp"),
    SECRETS_INDEX_JSP("/WEB-INF/pages/secrets/index.jsp"),
    USERS_NEW_JSP("/WEB-INF/pages/users/new.jsp");

    private String path;

    InternalResource(final String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

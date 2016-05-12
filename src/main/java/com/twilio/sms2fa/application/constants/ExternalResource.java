package com.twilio.sms2fa.application.constants;

public enum ExternalResource {

    USERS("/users/"),
    USERS_NEW("/users/new/"),
    CONFIRMATIONS("/confirmations/"),
    CONFIRMATIONS_NEW("/confirmations/new/"),
    SECRETS("/secrets/"),
    SESSIONS("/sessions/"),
    SESSIONS_NEW("/sessions/new/"),
    LOGOUT("/logout/");

    private String path;

    ExternalResource(final String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

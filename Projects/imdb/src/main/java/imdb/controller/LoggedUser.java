package imdb.controller;

import org.springframework.stereotype.Component;

@Component
final class LoggedUser {

    private String loggedUserIdentity;

    boolean isNotLogged() {
        return this.loggedUserIdentity == null;
    }

    String getLoggedUserIdentity() {
        return this.loggedUserIdentity;
    }

    void loginUser(final String identity) {
        this.loggedUserIdentity = identity;
    }

    void logoutUser() {
        this.loggedUserIdentity = null;
    }
}
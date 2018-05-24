package imdb.controller;

import org.springframework.stereotype.Component;

@Component
class LoggedUser {

    private String loggedUserIdentity;

    boolean isUserLogged() {
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
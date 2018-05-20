package bg.mobile.user.controller;

import org.springframework.stereotype.Component;

@Component
public class LoggedUser {

    private String loggedUsername;

    public boolean isUserLogged() {
        return this.loggedUsername == null;
    }

    public String getLoggedUsername() {
        return this.loggedUsername;
    }

    public void loginUser(final String username) {
        this.loggedUsername = username;
    }

    public void logoutUser() {
        this.loggedUsername = null;
    }
}
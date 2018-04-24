package library.controller;

import org.springframework.stereotype.Component;

@Component
public class SimpleLoggedUserRegister {

    private String loggedUserName;

    public boolean noLoggedUser() {
        return this.loggedUserName == null;
    }

    public String getLoggedUserName() {
        return this.loggedUserName;
    }

    public void loginUser(final String userName) {
        this.loggedUserName = userName;
    }

    public void logoutUser() {
        this.loggedUserName = null;
    }
}

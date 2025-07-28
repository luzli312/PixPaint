package interface_adapter.logged_in;

import app.Main;
import entity.User;

/**
 * This class keeps track of the user currently logged in. It stores the current User object.
 */
public class LoggedInState {
    private final User user;

    public LoggedInState(User currentUser) {
        this.user = currentUser;
    }

    public void execute() {
        Main.closeStartupWindow();
        Main.openPixPaint(user.getUsername());
    }
}

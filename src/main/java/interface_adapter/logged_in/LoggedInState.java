package interface_adapter.logged_in;

import app.Main;
import entity.User;

/**
 * This class keeps track of the currentUser currently logged in. It stores the current User object.
 */
public class LoggedInState {
    private static User currentUser = null;

    private LoggedInState() {
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void execute() {
        Main.closeStartupWindow();
        Main.openPixPaint(currentUser.getUsername());
    }

    public static User getCurrentUser() {
        return currentUser;
    }

}

package interfaceadapter.logged_in;

import app.Main;
import entity.User;

/**
 * This class keeps track of the currentUser currently logged in. It stores the current User object.
 */
public final class LoggedInState {
    // currentUser has a default value of null, so not explicitly initialized.
    private static User currentUser;

    private LoggedInState() {
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * Executes opening the app after clicking login.
     */
    public static void execute() {
        Main.closeStartupWindow();
        Main.openPixPaint(currentUser.getUsername());
    }

    /**
     * Logs out, closes the drawing window, and opens the startup window.
     */
    public static void logout() {
        currentUser = null;
        Main.closePixPaint();
        Main.main(null);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

}

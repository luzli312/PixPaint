package app;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import view.PixelArtView;
import view.StartupWindow;

public class Main {

    private static JFrame startupWindow;
    private static JFrame pixPaint;

    /**
     * Creates the StartUpWindow to start running app.
     * @param args unused arguments.
     */
    public static void main(String[] args) {
        startupWindow = new StartupWindow();
    }

    /**
     * Method to open the main Canvas Grid application.
     * @param username inputs the username of the user.
     */
    public static void openPixPaint(String username) {
        pixPaint = new JFrame(username + "'s PixPaint!");
        final int width = 1000;
        final int height = 800;
        pixPaint.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // when first opening the app have grid input be input white
        pixPaint.add(new PixelArtView(username));
        pixPaint.setSize(width, height);
        pixPaint.setLocationRelativeTo(null);
        pixPaint.setVisible(true);
    }

    /**
     * Method to close the main Canvas Grid application.
     */
    public static void closePixPaint() {
        if (pixPaint != null) {
            pixPaint.dispose();
        }
    }

    /**
     * Closes the application and start up window.
     */
    public static void closeStartupWindow() {
        if (startupWindow != null) {
            startupWindow.dispose();
        }
    }
}

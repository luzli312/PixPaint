package app;

import view.PixelArtView;
import view.StartupWindow;

import javax.swing.*;

public class Main {

    private static JFrame startupWindow;

    public static void main(String[] args) {
        startupWindow = new StartupWindow();
    }

    public static void openPixPaint(String username) {
        JFrame pixPaint = new JFrame(username + "'s PixPaint!");
        pixPaint.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        pixPaint.add(new PixelArtView());
        pixPaint.setSize(900, 800);
        pixPaint.setVisible(true);
    }

    public static void closeStartupWindow() {
        startupWindow.dispose();
    }
}

package app;

import view.PixelArtView;
import view.StartupWindow;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    private static JFrame startupWindow;

    public static void main(String[] args) {
        startupWindow = new StartupWindow();
    }

    public static void openPixPaint(String username) {
        JFrame pixPaint = new JFrame(username + "'s PixPaint!");
        pixPaint.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // when first opening the app have grid input be input white
        pixPaint.add(new PixelArtView(username));
        pixPaint.setSize(1000, 800);
        pixPaint.setLocationRelativeTo(null);
        pixPaint.setVisible(true);
    }

    public static void closeStartupWindow() {
        startupWindow.dispose();
    }
}

package app;

import view.PixelArtView;
import view.StartupWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        new StartupWindow();
    }

    public static void openPixPaint() {
        JFrame pixPaint = new JFrame("PixPaint!");
        pixPaint.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        pixPaint.add(new PixelArtView());
        pixPaint.setSize(900, 800);
        pixPaint.setVisible(true);
    }
}

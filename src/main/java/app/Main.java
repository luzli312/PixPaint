package app;

import interface_adapter.canvas_grid.ChangeColorController;
import view.CanvasGridPanel;
import view.LoginView;
import view.SignupView;
import view.StartupWindow;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        new StartupWindow();
    }

    public static void openPixPaint() {
        JFrame pixPaint = new JFrame("PixPaint!");
        pixPaint.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        pixPaint.add(new CanvasGridPanel("#000000", new ChangeColorController()));
        pixPaint.pack();
        pixPaint.setVisible(true);
    }
}

package app;

import view.LoginView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        final JFrame application = new JFrame("Welcome to PixPaint!");
        application.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        application.add(new LoginView());
        application.setSize(300, 200);
        application.setVisible(true);
    }
}

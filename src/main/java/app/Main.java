package app;

import view.LoginView;
import view.SignupView;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        final JFrame application = new JFrame("Welcome to PixPaint!");
        application.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Create a CardLayout JPanel with the login and signup views available.
        CardLayout cards = new CardLayout();
        JPanel views = new JPanel(cards);
        views.add(new LoginView(views, cards), "Login");
        views.add(new SignupView(views, cards), "Signup");

        // Show the login screen by default.
        cards.show(views, "Login");

        // Add the JPanel to the frame and make it visible.
        application.add(views);
        application.pack();
        application.setVisible(true);
    }
}

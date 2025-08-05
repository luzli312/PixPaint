package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartupWindow extends JFrame {
    private final CardLayout cards = new CardLayout();
    private final JPanel views = new JPanel(cards);

    public StartupWindow() {
        this.setTitle("Welcome to PixPaint!");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        views.add(new LoginView(this, views, cards), "Login");
        views.add(new SignupView(views, cards), "Signup");

        // Show the login screen by default.
        cards.show(views, "Login");

        // Add the JPanel to the frame and make it visible.
        this.add(views);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

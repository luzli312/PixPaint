package view;

import app.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class contains the JPanel setup for the user login view.
 */
public class LoginView extends JPanel {
    // Create instance variables for the displays in the JPanel.
    private final JLabel loginTitle = new JLabel("Login to PixPaint");
    private final LabelFieldPanel usernameInfo;
    private final LabelFieldPanel passwordInfo;
    private final JLabel username = new JLabel("Username:");
    private final JLabel password = new JLabel("Password:");
    private final JTextField usernameInput = new JTextField(15);
    private final JTextField passwordInput = new JTextField(15);
    private final JButton toLogin = new JButton("Login");
    private final JButton toSignUp = new JButton("Sign Up");

    // Instance variable storing the main window, panels, and layout so that the action listeners
    // can call them to change the current view.
    private final JFrame startWindow;
    private final JPanel views;
    private final CardLayout cards;

    public LoginView (JFrame start, JPanel views, CardLayout cards) {
        this.startWindow = start;
        this.views = views;
        this.cards = cards;

        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameInfo = new LabelFieldPanel(username, usernameInput);
        passwordInfo = new LabelFieldPanel(password, passwordInput);

        final JPanel loginButtons = new JPanel();
        loginButtons.add(toLogin);
        loginButtons.add(toSignUp);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(loginTitle);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(loginButtons);

        // Add action listener so that the Signup button will change the view to the
        // signup view.
        toSignUp.addActionListener(
                new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    cards.show(views, "Signup");
                }
        }
        );

        // Add action listener for Login button to redirect to main drawing panel.
        toLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        start.dispose();
                        Main.openPixPaint();
                    }
                }
        );

    }

}

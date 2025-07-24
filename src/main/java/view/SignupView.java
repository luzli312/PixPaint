package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class contains the JPanel setup for the user signup view.
 */
public class SignupView extends JPanel {
    // Create instance variables for the displays in the JPanel.
    private final JLabel signupTitle = new JLabel("Signup for PixPaint:");
    private final LabelFieldPanel usernameInfo;
    private final LabelFieldPanel passwordInfo;
    private final LabelFieldPanel passwordConfirm;
    private final JLabel username = new JLabel("Username:");
    private final JLabel password = new JLabel("Password:");
    private final JLabel confirmPassword = new JLabel("Confirm Password:");
    private final JTextField usernameInput = new JTextField(15);
    private final JTextField passwordInput = new JTextField(15);
    private final JTextField passwordConfirmInput = new JTextField(15);
    private final JButton toSignup = new JButton("Sign Up");
    private final JButton toLogin = new JButton("Login");

    // Instance variable storing the main window, panels, and layout so that the action listeners
    // can call them to change the current view.
    private final JFrame startWindow;
    private final JPanel views;
    private final CardLayout cards;

    public SignupView(JFrame start, JPanel views, CardLayout cards) {
        this.startWindow = start;
        this.views = views;
        this.cards = cards;

        signupTitle.setAlignmentX(CENTER_ALIGNMENT);

        usernameInfo = new LabelFieldPanel(username, usernameInput);
        passwordInfo = new LabelFieldPanel(password, passwordInput);
        passwordConfirm = new LabelFieldPanel(confirmPassword, passwordConfirmInput);

        final JPanel signupButtons = new JPanel();
        signupButtons.add(toSignup);
        signupButtons.add(toLogin);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(signupTitle);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(passwordConfirm);
        this.add(signupButtons);

        // Add action listener so that the Login button will change the view to the
        // login view.
        toLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cards.show(views, "Login");
                    }
        }
        );
    }

}

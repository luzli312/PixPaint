package view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import use_case.login.LoginInteractor;

/**
 * This class contains the JPanel setup for the user login view.
 */
public class LoginView extends JPanel implements ActionListener {
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

    // Instance variable storing the panels, and layout so that the action listeners
    // can call them to change the current view.
    private final JPanel views;
    private final CardLayout cards;

    public LoginView(JFrame start, JPanel views, CardLayout cards) {
        this.views = views;
        this.cards = cards;

        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameInfo = new LabelFieldPanel(username, usernameInput);
        passwordInfo = new LabelFieldPanel(password, passwordInput);

        final JPanel loginButtons = new JPanel();
        loginButtons.add(toLogin);
        loginButtons.add(toSignUp);
        // Add action listeners for the login and signup buttons.
        toLogin.addActionListener(this);
        toSignUp.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(loginTitle);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(loginButtons);

    }

    /**
     * Action listeners for the Login and Signup buttons.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Sends login input to the LoginInteractor to check validity.
        if (e.getSource() == toLogin) {
            new LoginInteractor(usernameInput.getText(), passwordInput.getText()).execute();
        }
        else if (e.getSource() == toSignUp) {
            cards.show(views, "Signup");
        }
    }

}

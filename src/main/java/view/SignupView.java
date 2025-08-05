package view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import usecase.signup.SignupInputData;
import usecase.signup.SignupInteractor;

/**
 * This class contains the JPanel setup for the user signup view.
 */
public class SignupView extends JPanel implements ActionListener {
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

        // Adding action listeners to the buttons.
        toLogin.addActionListener(this);
        toSignup.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(signupTitle);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(passwordConfirm);
        this.add(signupButtons);

    }

    /**
     * Action listeners for the Login and Signup buttons.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Switches to the login view once the Login button is clicked.
        if (e.getSource() == toLogin) {
            cards.show(views, "Login");
        }
        else if (e.getSource() == toSignup) {
            final SignupInputData input = new SignupInputData(usernameInput.getText(), passwordInput.getText(),
                    passwordConfirmInput.getText());
            new SignupInteractor(input).execute();
        }
    }

}

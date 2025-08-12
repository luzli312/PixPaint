package view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import usecase.signup.SignupInteractor;

/**
 * This class contains the JPanel setup for the user signup view.
 */
public class SignupView extends JPanel implements ActionListener {
    // Create instance variables for the displays in the JPanel.
    private final JLabel signupTitle = new JLabel("Signup for PixPaint:");
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

    private final JPanel views;
    private final CardLayout cards;

    public SignupView(JPanel views, CardLayout cards) {
        this.views = views;
        this.cards = cards;

        signupTitle.setAlignmentX(CENTER_ALIGNMENT);

        final JPanel inputFields = new JPanel();
        final GroupLayout inputLayout = new GroupLayout(inputFields);
        inputFields.setLayout(inputLayout);

        // Create gaps between components and between the components and container.
        inputLayout.setAutoCreateGaps(true);
        inputLayout.setAutoCreateContainerGaps(true);

        // Create a sequential group for the vertical and horizontal axes.
        final GroupLayout.SequentialGroup vertical = inputLayout.createSequentialGroup();
        final GroupLayout.SequentialGroup horizontal = inputLayout.createSequentialGroup();

        // Align labels and text field pairs vertically.
        vertical.addGroup(inputLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(username).addComponent(usernameInput));
        vertical.addGroup(inputLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(password).addComponent(passwordInput));
        vertical.addGroup(inputLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(confirmPassword).addComponent(passwordConfirmInput));
        inputLayout.setVerticalGroup(vertical);

        // Align labels with other labels and text fields with other fields horizontally.
        horizontal.addGroup(inputLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(username).addComponent(password).addComponent(confirmPassword));
        horizontal.addGroup(inputLayout.createParallelGroup()
                .addComponent(usernameInput).addComponent(passwordInput).addComponent(passwordConfirmInput));
        inputLayout.setHorizontalGroup(horizontal);

        final JPanel signupButtons = new JPanel();
        signupButtons.add(toSignup);
        signupButtons.add(toLogin);

        // Adding action listeners to the buttons.
        toLogin.addActionListener(this);
        toSignup.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(signupTitle);
        this.add(inputFields);
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
            new SignupInteractor(usernameInput.getText(), passwordInput.getText(),
                    passwordConfirmInput.getText()).execute();
        }
    }

}

package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {

    private final JLabel loginTitle = new JLabel("Login to PixPaint");
    private final LabelFieldPanel usernameInfo;
    private final LabelFieldPanel passwordInfo;
    private final JLabel username = new JLabel("Username:");
    private final JLabel password = new JLabel("Password:");
    private final JTextField usernameInput = new JTextField(15);
    private final JTextField passwordInput = new JTextField(15);
    private final JButton toLogin = new JButton("Login");
    private final JButton toSignUp = new JButton("Sign Up");

    public LoginView () {
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
    }

}

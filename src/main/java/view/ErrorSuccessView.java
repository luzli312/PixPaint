package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorSuccessView extends JFrame implements ActionListener {

    public ErrorSuccessView(String responseType, String message) {
        this.setTitle(responseType);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.add(new JLabel(message));

        final JButton close = new JButton("OK");
        this.add(close);

        // Adding an action listener to the button.
        close.addActionListener(this);

        this.pack();
        this.setVisible(true);
    }

    /**
     * Making the window close upon click.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}

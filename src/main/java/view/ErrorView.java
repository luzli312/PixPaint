package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorView extends JFrame implements ActionListener {

    public ErrorView(String errorType, String errorMessage) {
        this.setTitle(errorType);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.add(new JLabel(errorMessage));

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

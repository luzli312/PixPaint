package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class is a panel containing a label and a text field, such that the label is
 * describing what the text field is for.
 * This provides a quick way to construct signup, login views.
 */

public class LabelFieldPanel extends JPanel {
        LabelFieldPanel(JLabel fieldLabel, JTextField textField) {
            this.add(fieldLabel);
            this.add(textField);
        }
}

package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import dataaccess.UserDataAccessObject;

public class LoadPanel extends JPanel {
    private static final Integer HEADER_SIZE = 35;

    private final JLabel loadTitle = new JLabel("Select Your PixPaint Project:");
    private final ArrayList<String> projectList;
    private final JPanel projectBoxes;
    private final Integer maxLength;

    public LoadPanel(String username, JButton toCanvas) throws IOException {
        this.projectList = new UserDataAccessObject().getProjectNames(username);

        loadTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel projBoxes = new JPanel();
        projBoxes.setAlignmentX(Component.CENTER_ALIGNMENT);
        projBoxes.setLayout(new BoxLayout(projBoxes, BoxLayout.Y_AXIS));
        final ButtonGroup projButtons = new ButtonGroup();
        int length = HEADER_SIZE;
        for (String title : projectList) {
            if (title.length() > length) {
                length = title.length();
            }
            final JRadioButton radioButton = new JRadioButton(title);
            radioButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            projButtons.add(radioButton);
            projBoxes.add(radioButton);
        }
        this.maxLength = length;

        final JPanel loadButtons = new JPanel();
        loadButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        loadButtons.setAlignmentX(Component.LEFT_ALIGNMENT);
        loadButtons.add(toCanvas, BorderLayout.SOUTH);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(loadTitle);
        this.add(projBoxes, BorderLayout.WEST);
        this.add(loadButtons, BorderLayout.SOUTH);
        this.projectBoxes = projBoxes;
    }

    public JPanel getProjectBoxes() {
        return projectBoxes;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public ArrayList<String> getProjectList() {
        return projectList;
    }
}

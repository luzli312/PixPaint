package view;

import app.Main;
import data_access.UserDataAccessObject;
import entity.User;
import interface_adapter.canvas_grid.ChangeColorController;
import org.bson.Document;
import usecase.load_canvas.LoadCanvasInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class LoadView extends JPanel {
    private final JLabel loadTitle = new JLabel("Select Your PixPaint Project:");
    private final ArrayList<String> projectList;
    private final JButton toCanvas = new JButton("Ok");

    // Instance variable storing the main window, panels, and layout so that the action listeners
    // can call them to change the current view.
    private final JFrame startFrame = new JFrame("Load Project");
    private final JPanel panel;
    private final CardLayout layout;

    public LoadView (String username, JPanel view, CardLayout layout, CanvasGridPanel canvasGridPanel) throws IOException {
        this.projectList = UserDataAccessObject.getProjectNames(username);
        this.panel = view;
        this.layout = layout;

        loadTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel projectBoxes = new JPanel();
        projectBoxes.setAlignmentX(Component.RIGHT_ALIGNMENT);
        projectBoxes.setLayout(new BoxLayout(projectBoxes, BoxLayout.Y_AXIS));
        final ButtonGroup projButtons = new ButtonGroup();
        int max_length = 35;
        for (String title : projectList) {
            if (title.length() > max_length) {
                max_length = title.length();
            }
            JRadioButton radioButton = new JRadioButton(title);
            radioButton.setLayout(new FlowLayout(FlowLayout.LEADING));
            radioButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
            projButtons.add(radioButton);
            projectBoxes.add(radioButton);
        }

        final JPanel loadButtons = new JPanel();
        // loadButtons.setSize(max_length, projectList.size() + 10);
        loadButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        loadButtons.setAlignmentX(Component.LEFT_ALIGNMENT);
        loadButtons.add(toCanvas, BorderLayout.SOUTH);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(loadTitle);
        this.add(projectBoxes, BorderLayout.WEST);
        this.add(loadButtons, BorderLayout.SOUTH);

        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startFrame.add(this);
        startFrame.setSize(max_length*10, (projectList.size() + 12)*10);
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);

        // action listener for Ok button to redirect to main Canvas Frame,
        // and changes the grid view to that stored from the project.
        toCanvas.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        for (Component component : projectBoxes.getComponents()) {
                            if (component instanceof JRadioButton && ((JRadioButton) component).isSelected()) {
                                    String projname = ((JRadioButton) component).getText();
                                    // Main.openPixPaint(username, GetSavedProject.getProject(username, projname));
                                    new LoadCanvasInteractor().execute(username, canvasGridPanel, projname);
                                    startFrame.dispose();
                                }

                        }
                    }
                });

    }
}

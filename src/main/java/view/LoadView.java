package view;

import app.Main;
import data_access.UserDataAccessObject;
import entity.User;
import interface_adapter.canvas_grid.ChangeColorController;
import org.bson.Document;
import usecase.GetSavedProject;
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

    // private CanvasGridPanel canvasGridPanel;

    public LoadView (String username, JPanel view, CardLayout layout, CanvasGridPanel canvasGridPanel) throws IOException {
        this.projectList = UserDataAccessObject.getProjectNames(username);
        this.panel = view;
        this.layout = layout;

        // canvasGridPanel = new CanvasGridPanel(new ChangeColorController());

        loadTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel projectBoxes = new JPanel();
        projectBoxes.setLayout(new BoxLayout(projectBoxes, BoxLayout.Y_AXIS));
        final ButtonGroup projButtons = new ButtonGroup();
        for (String title : projectList) {
            JRadioButton radioButton = new JRadioButton(title);
            projButtons.add(radioButton);
            projectBoxes.add(radioButton);
        }

        final JPanel loadButtons = new JPanel();
        loadButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        loadButtons.setAlignmentX(Component.LEFT_ALIGNMENT);
        loadButtons.add(toCanvas, BorderLayout.SOUTH);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(loadTitle);
        this.add(projectBoxes, BorderLayout.WEST);
        this.add(loadButtons, BorderLayout.SOUTH);

        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startFrame.add(this);
        startFrame.setSize(600, 400);
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
                                    System.out.println(projname);
                                }

                        }
                    }
                });

    }
}

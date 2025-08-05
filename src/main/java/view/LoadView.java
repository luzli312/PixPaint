package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import data_access.UserDataAccessObject;
import interface_adapter.load.LoadController;
import usecase.load_canvas.LoadCanvasInteractor;

public class LoadView extends JPanel {
    private static final Integer HEADER_SIZE = 35;
    private static final Integer RESIZE = 10;
    private static final Integer BUTTON_SIZE = 12;

    private final JLabel loadTitle = new JLabel("Select Your PixPaint Project:");
    private final ArrayList<String> projectList;
    private final JButton toCanvas = new JButton("Ok");

    // Instance variable storing the main window, panels, and layout so that the action listeners
    // can call them to change the current view.
    private final JFrame startFrame = new JFrame("Load Project");
    private final JPanel panel;
    private final CardLayout layout;

    public LoadView(String username, JPanel view, CardLayout layout, CanvasGridPanel canvasGridPanel,
                    LoadController loadController) throws IOException {
        this.projectList = UserDataAccessObject.getProjectNames(username);
        this.panel = view;
        this.layout = layout;

        loadTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel projectBoxes = new JPanel();
        projectBoxes.setAlignmentX(Component.CENTER_ALIGNMENT);
        projectBoxes.setLayout(new BoxLayout(projectBoxes, BoxLayout.Y_AXIS));
        final ButtonGroup projButtons = new ButtonGroup();
        int maxLength = HEADER_SIZE;
        for (String title : projectList) {
            if (title.length() > maxLength) {
                maxLength = title.length();
            }
            final JRadioButton radioButton = new JRadioButton(title);
            radioButton.setAlignmentX(Component.LEFT_ALIGNMENT);
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
        startFrame.setSize(maxLength * RESIZE, (projectList.size() + BUTTON_SIZE) * RESIZE);
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
                                final String projname = ((JRadioButton) component).getText();
                                new LoadCanvasInteractor().execute(username, canvasGridPanel, projname, loadController);
                                startFrame.dispose();
                            }

                        }
                    }
                });

    }
}

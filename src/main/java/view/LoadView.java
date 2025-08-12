package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import interface_adapter.load.LoadController;
import use_case.load_canvas.LoadCanvasInteractor;

public class LoadView extends JPanel {
    private static final Integer RESIZE = 10;
    private static final Integer BUTTON_SIZE = 15;

    private final JButton toCanvas = new JButton("Ok");

    private final JFrame startFrame = new JFrame("Load Project");

    public LoadView(String username, CanvasGridPanel canvasGridPanel,
                    LoadController loadController) throws IOException {
        final LoadPanel loadPanel = new LoadPanel(username, toCanvas);
        final JPanel projectBoxes = loadPanel.getProjectBoxes();

        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startFrame.add(loadPanel);
        startFrame.setSize(loadPanel.getMaxLength() * RESIZE, (
                loadPanel.getProjectList().size() + BUTTON_SIZE) * RESIZE);
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

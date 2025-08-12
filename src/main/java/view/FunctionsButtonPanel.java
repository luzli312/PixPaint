package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import interfaceadapter.export.ExportController;
import interfaceadapter.load.LoadController;
import interfaceadapter.save.SaveController;

public class FunctionsButtonPanel extends JPanel {
    private static final Integer HGAP = 15;
    private static final Integer VGAP = 5;

    public FunctionsButtonPanel(ExportController exportController,
                                CanvasGridPanel canvasGridPanel, PixelArtView pixelArtView, String username) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, HGAP, VGAP));

        final JButton saveButton = new JButton("Save");
        final JButton loadButton = new JButton("Load");
        final JButton exportButton = new JButton("Export");

        this.add(saveButton);
        this.add(loadButton);
        this.add(exportButton);

        /*
        exportButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        exportController.execute(canvasGridPanel, loadController.getCurrentProject());
                    }
                }
        );
*/
        // Adding action listeners to the save and load buttons.
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String projectName = JOptionPane.showInputDialog(
                        pixelArtView, "Enter project name:", "Save Project", JOptionPane.PLAIN_MESSAGE);
                new SaveController(projectName).execute(pixelArtView, canvasGridPanel);
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoadView(username, canvasGridPanel);
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}

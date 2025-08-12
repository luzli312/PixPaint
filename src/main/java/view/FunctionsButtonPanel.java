package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import interface_adapter.export.ExportController;
import interface_adapter.load.LoadController;
import interface_adapter.save.SaveController;

public class FunctionsButtonPanel extends JPanel {
    private static final Integer HGAP = 15;
    private static final Integer VGAP = 5;

    public FunctionsButtonPanel(JButton saveButton, JButton loadButton, JButton exportButton,
                                LoadController loadController, ExportController exportController,
                                CanvasGridPanel canvasGridPanel, PixelArtView pixelArtView) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, HGAP, VGAP));
        this.add(saveButton);
        this.add(loadButton);
        this.add(exportButton);

        exportButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        exportController.execute(canvasGridPanel, loadController.getCurrentProject());
                    }
                }
        );

        // Adding action listeners to the save and load buttons.
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SaveController().execute(pixelArtView, canvasGridPanel);
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadController.execute(canvasGridPanel);
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}

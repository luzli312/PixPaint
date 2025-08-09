package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import interfaceadapter.canvas_grid.ChangeColorController;

public class CanvasPanel extends JPanel {
    private static final Integer CANVAS_SIZE = 400;
    private CanvasGridPanel canvasGridPanel;

    public CanvasPanel() {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(CANVAS_SIZE, CANVAS_SIZE));
        this.canvasGridPanel = new CanvasGridPanel(new ChangeColorController());
        this.add(canvasGridPanel);
    }

    public CanvasGridPanel getCanvasGridPanel() {
        return canvasGridPanel;
    }
}

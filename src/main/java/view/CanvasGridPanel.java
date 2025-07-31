package view;


import interface_adapter.canvas_grid.ChangeColorController;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;

public class CanvasGridPanel extends JPanel {
    // Create 32 x 32 grid, white squares, maybe add black lines to separate each square.
    // JPanel
    private static final int LENGTH = 32;
    private static final int SIDE_LENGTH = 20;
    private static final int GAP = 1;
    private static final Color BORDER = Color.decode("#000000");
    private static final Color DEFAULT = Color.decode("#FFFFFF");
    private final ChangeColorController changeColorController;
    private final JButton[][] allCells = new JButton[32][32];

    public CanvasGridPanel(ChangeColorController changeColorController)
    {
        this.changeColorController = changeColorController;
        setBackground(BORDER);
        setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setLayout(new GridLayout(LENGTH, LENGTH, GAP, GAP));
        Dimension prefSize = new Dimension(SIDE_LENGTH, SIDE_LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                JButton cell = new JButton();
                cell.setBackground(DEFAULT);
                cell.setName("transparent");
                cell.setPreferredSize(prefSize);
                add(cell);
                cell.addActionListener(e -> changeColorController.execute((JButton) e.getSource()));
                allCells[i][j] = cell;
            }
        }
    }

    public ChangeColorController getChangeColorController() {
        return changeColorController;
    }

    public JButton[][] getAllCells() {
        return allCells;
    }

    public void loadCanvasGridPanel(Color[][] loadData) {
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (loadData[i][j].getAlpha() == 0) {
                    allCells[i][j].setBackground(DEFAULT);
                    allCells[i][j].setName("transparent");
                }
                else {
                    allCells[i][j].setBackground(loadData[i][j]);
                    allCells[i][j].setName("Opaque");
                }
            }
        }
    }

}


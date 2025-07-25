package view;

import interface_adapter.canvas_grid.ChangeColorController;
import view.CanvasGridPanel;
import javax.swing.*;
import java.awt.*;

public class PixelArtView extends JPanel {
    private final JPanel canvasPanel = new JPanel();
    private final JPanel palettePanel = new JPanel();

    private final JButton brushButton;
    private final JButton eraserButton;

    private final JButton saveButton = new JButton("Save");
    private final JButton loadButton = new JButton("Load");

    private final CanvasGridPanel canvasGridPanel;

    public PixelArtView() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        canvasPanel.setBackground(Color.WHITE);
        canvasPanel.setPreferredSize(new Dimension(400, 400));
        canvasGridPanel = new CanvasGridPanel("#000000", new ChangeColorController());
        canvasPanel.add(canvasGridPanel);

        palettePanel.setLayout(new GridLayout(5, 3, 5, 5));
        palettePanel.setPreferredSize(new Dimension(100, 100));

        Color[] colors = {
                Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE,
                Color.CYAN, Color.MAGENTA, Color.PINK, Color.DARK_GRAY, Color.BLACK
        };

        for (Color color : colors) {
            JButton colorTile = new JButton();
            colorTile.setBackground(color);
            colorTile.setOpaque(true);
            colorTile.setBorderPainted(false);
            colorTile.setFocusPainted(false);
            colorTile.setContentAreaFilled(true);
            colorTile.setPreferredSize(new Dimension(40, 40));
            colorTile.setMaximumSize(new Dimension(40, 40));
            colorTile.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            palettePanel.add(colorTile);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        ImageIcon brushIcon = new ImageIcon(getClass().getResource("/brush.png"));
        ImageIcon eraserIcon = new ImageIcon(getClass().getResource("/eraser.png"));

        brushButton = new JButton(brushIcon);
        brushButton.setPreferredSize(new Dimension(50, 50));
        brushButton.setToolTipText("Brush Tool");

        eraserButton = new JButton(eraserIcon);
        eraserButton.setPreferredSize(new Dimension(50, 50));
        eraserButton.setToolTipText("Eraser Tool");

        JPanel toolPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        toolPanel.add(brushButton);
        toolPanel.add(eraserButton);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout(10, 10));
        rightPanel.add(toolPanel, BorderLayout.NORTH);
        rightPanel.add(palettePanel, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(canvasPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
    }

}

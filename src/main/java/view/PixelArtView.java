package view;

import entity.CanvasData;
import interface_adapter.canvas_grid.ChangeColorController;
import interface_adapter.load.LoadController;
import usecase.color_canvas.PaletteSelection;
import usecase.load_canvas.LoadCanvasInteractor;
import usecase.save_canvas.SaveCanvasInteractor;
import view.CanvasGridPanel;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PixelArtView extends JPanel implements ActionListener {
    private final JPanel canvasPanel = new JPanel();
    private final JPanel palettePanel = new JPanel();

    private final JButton brushButton;
    private final JButton eraserButton;

    private final JButton saveButton = new JButton("Save");
    private final JButton loadButton = new JButton("Load");

    private CanvasGridPanel canvasGridPanel;

    public PixelArtView(String username) {
        final LoadController loadController = new LoadController(username);

        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        canvasPanel.setBackground(Color.WHITE);
        canvasPanel.setPreferredSize(new Dimension(400, 400));
        canvasGridPanel = new CanvasGridPanel(new ChangeColorController());
        canvasPanel.add(canvasGridPanel);

        palettePanel.setLayout(new GridLayout(5, 3, 5, 5));
        palettePanel.setPreferredSize(new Dimension(100, 100));

        Color[] colors = {
                Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE,
                Color.CYAN, Color.MAGENTA, Color.PINK, Color.DARK_GRAY, Color.BLACK
        };

        // Create new PaletteSelection object to store selected color from palette.
        PaletteSelection paletteSelection = new PaletteSelection(canvasGridPanel);

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

            // Add an action listener so that clicking the color tile changes the palette selection.
            colorTile.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (eraserButton.getBorder() == BorderFactory.createBevelBorder(BevelBorder.LOWERED)) {
                                eraserButton.setBorderPainted(true);
                                eraserButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                            }
                            colorTile.setBorderPainted(true);
                            colorTile.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                            paletteSelection.setCurrentSelection(colorTile);
                        }
                    }
            );
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        // Adding action listeners to the save and load buttons.
        saveButton.addActionListener(this);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadController.execute(canvasGridPanel);
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
                }
            }
        });

        ImageIcon brushIcon = new ImageIcon(getClass().getResource("/brush.png"));
        ImageIcon eraserIcon = new ImageIcon(getClass().getResource("/eraser.png"));

        brushButton = new JButton(brushIcon);
        brushButton.setBorderPainted(true);
        brushButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        brushButton.setPreferredSize(new Dimension(50, 50));
        brushButton.setToolTipText("Brush Tool");

        eraserButton = new JButton(eraserIcon);
        eraserButton.setBorderPainted(true);
        eraserButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        eraserButton.setPreferredSize(new Dimension(50, 50));
        eraserButton.setToolTipText("Eraser Tool");
        eraserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paletteSelection.getCurrentSelection() != null &&
                        paletteSelection.getCurrentSelection().getBorder() ==
                                BorderFactory.createBevelBorder(BevelBorder.LOWERED)) {
                    paletteSelection.getCurrentSelection().setBorderPainted(false);
                    paletteSelection.getCurrentSelection().setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                }
                eraserButton.setBorderPainted(true);
                eraserButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                canvasGridPanel.getChangeColorController().setCurrentColor(Color.WHITE);
            }
        });

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==saveButton) {
            new SaveCanvasInteractor().execute(canvasGridPanel);
        }
    }
}

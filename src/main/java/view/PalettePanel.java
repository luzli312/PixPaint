package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import interfaceadapter.export.ExportController;
import interfaceadapter.load.LoadController;
import usecase.color_canvas.PaletteSelection;

public class PalettePanel extends JPanel {
    private static final Integer PALETTE_ROWS = 5;
    private static final Integer PALETTE_COLS = 3;
    private static final Integer PALETTE_GAP = 5;
    private static final Integer PALETTE_SIZE = 100;
    private static final Integer COLORTILE_SIZE = 40;

    private final JButton brushButton;
    private final JButton eraserButton;
    private final JButton logoutButton;

    private final JButton saveButton = new JButton("Save");
    private final JButton loadButton = new JButton("Load");
    private final JButton exportButton = new JButton("Export");

    private final JPanel buttonPanel;
    private final JPanel toolPanel;

    public PalettePanel(CanvasGridPanel canvasGridPanel, LoadController loadController,
                        ExportController exportController, PixelArtView pixelArtView) {
        this.setLayout(new GridLayout(PALETTE_ROWS, PALETTE_COLS, PALETTE_GAP, PALETTE_GAP));
        this.setPreferredSize(new Dimension(PALETTE_SIZE, PALETTE_SIZE));

        final Color[] colors = {
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE,
            Color.CYAN, Color.MAGENTA, Color.PINK, Color.DARK_GRAY, Color.BLACK,
        };

        // Create new PaletteSelection object to store selected color from palette.
        final PaletteSelection paletteSelection = new PaletteSelection(canvasGridPanel);

        for (Color color : colors) {
            final JButton colorTile = new JButton();
            colorTile.setBackground(color);
            colorTile.setOpaque(true);
            colorTile.setBorderPainted(true);
            colorTile.setFocusPainted(false);
            colorTile.setContentAreaFilled(true);
            colorTile.setPreferredSize(new Dimension(COLORTILE_SIZE, COLORTILE_SIZE));
            colorTile.setMaximumSize(new Dimension(COLORTILE_SIZE, COLORTILE_SIZE));
            colorTile.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            this.add(colorTile);

            // Add an action listener so that clicking the color tile changes the palette selection.
            colorTile.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // If the eraser button has not been selected, selecting a color will result in painting
                            // that color.
                            if (eraserButton.getBorder() != BorderFactory.createBevelBorder(BevelBorder.LOWERED)) {
                                paletteSelection.setCurrentSelection(colorTile);
                                colorTile.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                            }
                            // If the eraser button has been selected, you can still change what color is selected
                            // but the color being painted is still white because you have not switched to the brush.
                            else {
                                paletteSelection.changeSelectionIndicator(colorTile);
                            }
                        }
                    }
            );
        }

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException
               | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        this.buttonPanel = new FunctionsButtonPanel(saveButton, loadButton, exportButton,
                loadController, exportController, canvasGridPanel, pixelArtView);

        brushButton = new JButton();
        eraserButton = new JButton();
        logoutButton = new JButton("Logout");

        this.toolPanel = new ToolPanel(eraserButton, brushButton, logoutButton, canvasGridPanel, paletteSelection);
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public JPanel getToolPanel() {
        return toolPanel;
    }
}

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import interfaceadapter.palette.ColorChooser;
import interfaceadapter.export.ExportController;
import interfaceadapter.load.LoadController;
import usecase.colorcanvas.PaletteSelection;

public class PalettePanel extends JPanel {

    private static final Integer PALETTE_ROWS = 5;
    private static final Integer PALETTE_COLS = 3;
    private static final Integer PALETTE_GAP = 5;
    private static final Integer PALETTE_SIZE = 100;
    private static final Integer COLORTILE_SIZE = 40;
    private static final Integer COLORTILES = 10;

    private final JButton brushButton;
    private final JButton eraserButton;
    private final JButton logoutButton;

    private final JPanel buttonPanel;
    private final JPanel toolPanel;

    private final JButton[] colorTiles = new JButton[COLORTILES];

    private final PaletteSelection paletteSelection;

    public PalettePanel(CanvasGridPanel canvasGridPanel,
                        ExportController exportController, PixelArtView pixelArtView, String username) {
        this.setLayout(new GridLayout(PALETTE_ROWS, PALETTE_COLS, PALETTE_GAP, PALETTE_GAP));
        this.setPreferredSize(new Dimension(PALETTE_SIZE, PALETTE_SIZE));

        final Color[] colors = {
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE,
            Color.CYAN, Color.MAGENTA, Color.PINK, Color.DARK_GRAY, Color.BLACK,
        };

        // Create new PaletteSelection object to store selected color from palette.
        paletteSelection = new PaletteSelection(canvasGridPanel);

        for (int i = 0; i < COLORTILES; i++) {
            final JButton colorTile = new JButton();
            colorTile.setBackground(colors[i]);
            colorTile.setOpaque(true);
            colorTile.setBorderPainted(true);
            colorTile.setFocusPainted(false);
            colorTile.setContentAreaFilled(true);
            colorTile.setPreferredSize(new Dimension(COLORTILE_SIZE, COLORTILE_SIZE));
            colorTile.setMaximumSize(new Dimension(COLORTILE_SIZE, COLORTILE_SIZE));
            colorTile.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            this.add(colorTile);
            colorTiles[i] = colorTile;

            // Add an action listener so that left-clicking the color tile changes the palette selection.
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

            // Right click opens the color chooser dialog.
            colorTile.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent mouseEvent) {
                    if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                        ColorChooser.openColorChooserDialog(
                                PalettePanel.this,
                                colorTile,
                                paletteSelection,
                                canvasGridPanel.getChangeColorController()
                        );
                    }
                }
            });
        }

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException
               | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        this.buttonPanel = new FunctionsButtonPanel(exportController, canvasGridPanel, pixelArtView,
                username);

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

    public JButton[] getColorTiles() {
        return colorTiles;
    }

    public PaletteSelection getPaletteSelection() {
        return paletteSelection;
    }

    public JButton getBrushButton() {
        return brushButton;
    }

    public JButton getEraserButton() {
        return eraserButton;
    }

}

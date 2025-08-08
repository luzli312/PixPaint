package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import data_access.UserDataAccessObject;
import entity.CanvasData;
import entity.User;
import interface_adapter.canvas_grid.ChangeColorController;
import interface_adapter.export.ExportController;
import interface_adapter.load.LoadController;
import interface_adapter.logged_in.LoggedInState;
import usecase.color_canvas.PaletteSelection;
import usecase.save_canvas.SaveCanvasInteractor;

public class PixelArtView extends JPanel implements ActionListener {
    private static final Integer TOOL_SIZE = 50;
    private static final Integer BORDER_SIZE = 10;
    private static final Integer COLOR_NUM = 255;
    private static final Integer COLORTILE_SIZE = 40;
    private static final Integer PALETTE_SIZE = 100;
    private static final Integer CANVAS_SIZE = 400;
    private static final Integer PALETTE_ROWS = 5;
    private static final Integer PALETTE_COLS = 3;
    private static final Integer PALETTE_GAP = 5;

    private final JPanel canvasPanel = new JPanel();
    private final JPanel palettePanel = new JPanel();

    private final JButton brushButton;
    private final JButton eraserButton;
    private final JButton logoutButton;

    private final JButton saveButton = new JButton("Save");
    private final JButton loadButton = new JButton("Load");
    private final JButton exportButton = new JButton("Export");

    private CanvasGridPanel canvasGridPanel;

    public PixelArtView(String username) {
        final LoadController loadController = new LoadController(username);
        final ExportController exportController = new ExportController(username);

        this.setLayout(new BorderLayout(BORDER_SIZE, BORDER_SIZE));
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        canvasPanel.setBackground(Color.WHITE);
        canvasPanel.setPreferredSize(new Dimension(CANVAS_SIZE, CANVAS_SIZE));
        canvasGridPanel = new CanvasGridPanel(new ChangeColorController());
        canvasPanel.add(canvasGridPanel);

        palettePanel.setLayout(new GridLayout(PALETTE_ROWS, PALETTE_COLS, PALETTE_GAP, PALETTE_GAP));
        palettePanel.setPreferredSize(new Dimension(PALETTE_SIZE, PALETTE_SIZE));

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
            palettePanel.add(colorTile);

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
        catch (Exception ex) {
            ex.printStackTrace();
        }

        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(exportButton);
        exportButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        exportController.execute(canvasGridPanel, loadController.getCurrentProject());
                    }
                }
        );

        // Adding action listeners to the save and load buttons.
        saveButton.addActionListener(this);
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

        final ImageIcon brushIcon = new ImageIcon(getClass().getResource("/brush.png"));
        final ImageIcon eraserIcon = new ImageIcon(getClass().getResource("/eraser.png"));

        brushButton = new JButton(brushIcon);
        brushButton.setBorderPainted(true);
        brushButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        brushButton.setPreferredSize(new Dimension(TOOL_SIZE, TOOL_SIZE));
        brushButton.setToolTipText("Brush Tool");

        eraserButton = new JButton(eraserIcon);
        eraserButton.setBorderPainted(true);
        eraserButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        eraserButton.setPreferredSize(new Dimension(TOOL_SIZE, TOOL_SIZE));
        eraserButton.setToolTipText("Eraser Tool");

        logoutButton = new JButton("Logout");

        eraserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((BevelBorder) brushButton.getBorder()).getBevelType() == BevelBorder.LOWERED) {
                    brushButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                }
                eraserButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                canvasGridPanel.getChangeColorController().setCurrentColor(
                        new Color(COLOR_NUM, COLOR_NUM, COLOR_NUM, 0));
            }
        });

        // Adjust brush button bevel border when clicked.
        brushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((BevelBorder) eraserButton.getBorder()).getBevelType() == BevelBorder.LOWERED) {
                    eraserButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                    paletteSelection.sendCurrentSelection();
                }
                brushButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            }
        });

        // Make the logout button log the user out and change views.
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoggedInState.logout();
            }
        });

        final JPanel toolPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        toolPanel.add(brushButton);
        toolPanel.add(eraserButton);
        toolPanel.add(logoutButton);

        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout(BORDER_SIZE, BORDER_SIZE));
        rightPanel.add(toolPanel, BorderLayout.NORTH);
        rightPanel.add(palettePanel, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(canvasPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            final String projectName = JOptionPane.showInputDialog(
                    this, "Enter project name:", "Save Project", JOptionPane.PLAIN_MESSAGE);

            if (projectName == null || projectName.trim().isEmpty()) {
                new ErrorSuccessView("Error", "Empty project name!");
            }

            final UserDataAccessObject userDataAccessObject = new UserDataAccessObject();
            final User currentUser = LoggedInState.getCurrentUser();

            try {
                if (userDataAccessObject.projectExists(currentUser, projectName)) {
                    final int option = JOptionPane.showConfirmDialog(this,
                            "Project already exists. Overwrite?", "Overwrite",
                            JOptionPane.YES_NO_OPTION);
                    if (option != JOptionPane.YES_OPTION) {
                        return;
                    }
                    final CanvasData canvasData = new CanvasData(currentUser, projectName, canvasGridPanel);
                    userDataAccessObject.updateProject(canvasData.exportCanvasData());
                    new ErrorSuccessView("Success", "Project updated successfully!");
                }
                else {
                    new SaveCanvasInteractor().execute(currentUser, canvasGridPanel, projectName);
                    new ErrorSuccessView("Success", "Project saved successfully!");
                }
            }
            catch (Exception ex) {
                new ErrorSuccessView("Error", "Failed to save project: " + ex.getMessage());
            }
        }
    }
}

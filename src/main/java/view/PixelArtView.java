package view;

import entity.CanvasData;
import data_access.UserDataAccessObject;
import entity.User;
import interface_adapter.logged_in.LoggedInState;
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
            colorTile.setBorderPainted(true);
            colorTile.setFocusPainted(false);
            colorTile.setContentAreaFilled(true);
            colorTile.setPreferredSize(new Dimension(40, 40));
            colorTile.setMaximumSize(new Dimension(40, 40));
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
        brushButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
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
                if (((BevelBorder) brushButton.getBorder()).getBevelType() == BevelBorder.LOWERED) {
                    brushButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                }
                eraserButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                canvasGridPanel.getChangeColorController().setCurrentColor(Color.WHITE);
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
        if (e.getSource() == saveButton) {
            String projectName = JOptionPane.showInputDialog(
                    this, "Enter project name:", "Save Project", JOptionPane.PLAIN_MESSAGE);

            if (projectName == null || projectName.trim().isEmpty()) {
                new ErrorSuccessView("Error", "Empty project name!");
                return;
            }

            UserDataAccessObject userDataAccessObject = new UserDataAccessObject();
            User currentUser = LoggedInState.getCurrentUser();

            try {
                if (userDataAccessObject.projectExists(currentUser, projectName)) {
                    int option = JOptionPane.showConfirmDialog(this,
                            "Project already exists. Overwrite?", "Overwrite",
                            JOptionPane.YES_NO_OPTION);
                    if (option != JOptionPane.YES_OPTION) {
                        return;
                    }
                    CanvasData canvasData = new CanvasData(currentUser, projectName, canvasGridPanel);
                    userDataAccessObject.updateProject(canvasData.exportCanvasData());
                    new ErrorSuccessView("Success", "Project updated successfully!");
                } else {
                    new SaveCanvasInteractor().execute(currentUser, canvasGridPanel, projectName);
                    new ErrorSuccessView("Success", "Project saved successfully!");
                }
            } catch (Exception ex) {
                new ErrorSuccessView("Error", "Failed to save project: " + ex.getMessage());
            }
        }
    }
}

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import interfaceadapter.logged_in.LoggedInState;
import usecase.color_canvas.PaletteSelection;

public class ToolPanel extends JPanel {
    private static final Integer TOOL_SIZE = 50;
    private static final Integer COLOR_NUM = 255;
    private static final Integer HGAP = 10;
    private static final Integer VGAP = 5;

    public ToolPanel(JButton eraserButton, JButton brushButton, JButton logoutButton,
                     CanvasGridPanel canvasGridPanel, PaletteSelection paletteSelection) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, HGAP, VGAP));
        this.add(brushButton);
        this.add(eraserButton);
        this.add(logoutButton);

        final ImageIcon brushIcon = new ImageIcon(getClass().getResource("/brush.png"));
        final ImageIcon eraserIcon = new ImageIcon(getClass().getResource("/eraser.png"));

        brushButton.setIcon(brushIcon);
        brushButton.setBorderPainted(true);
        brushButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        brushButton.setPreferredSize(new Dimension(TOOL_SIZE, TOOL_SIZE));
        brushButton.setToolTipText("Brush Tool");

        eraserButton.setIcon(eraserIcon);
        eraserButton.setBorderPainted(true);
        eraserButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        eraserButton.setPreferredSize(new Dimension(TOOL_SIZE, TOOL_SIZE));
        eraserButton.setToolTipText("Eraser Tool");

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
    }
}

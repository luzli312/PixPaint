package usecase.color_canvas;

import view.CanvasGridPanel;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class PaletteSelection {

    private JButton currentSelection;
    private CanvasGridPanel canvasGridPanel;

    public PaletteSelection (CanvasGridPanel canvasGridPanel) {
        this.canvasGridPanel = canvasGridPanel;
    }

    // Changes what color tile is currently selected.
    public void setCurrentSelection (JButton newSelection) {
        // Checks if the tile that was clicked is a different tile than was selected before.
        if (this.currentSelection != newSelection) {
            if (this.currentSelection != null) {
                // Removes the border around the previous selection.
                currentSelection.setBorderPainted(false);
                currentSelection.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            }
            // Creates a border around the new selection to show the user they've successfully
            // changed colors.
            newSelection.setBorderPainted(true);
            newSelection.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            // Sets the current selection to the new selection.
            this.currentSelection = newSelection;
        }
        // Updates the current color in the ChangeColorController so that now any grid squares
        // clicked will fill with the currently selected color tile's color.
        canvasGridPanel.getChangeColorController().setCurrentColor(currentSelection.getBackground());
    }

    public JButton getCurrentSelection () {
        return currentSelection;
    }

}

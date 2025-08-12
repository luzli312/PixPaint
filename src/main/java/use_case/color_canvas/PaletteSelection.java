package use_case.color_canvas;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import view.CanvasGridPanel;

public class PaletteSelection {

    private JButton currentSelection;
    private CanvasGridPanel canvasGridPanel;

    public PaletteSelection(CanvasGridPanel canvasGridPanel) {
        this.canvasGridPanel = canvasGridPanel;
    }

    /**
     * Changes what color tile is currently selected.
     * @param newSelection inputs the color tile that is newly selected.
     */
    public void setCurrentSelection(JButton newSelection) {
        // Checks if the tile that was clicked is a different tile than was selected before.
        if (this.currentSelection != newSelection) {
            if (this.currentSelection != null) {
                // Raises the BevelBorder on the previous selection, indicating it is no longer selected.
                currentSelection.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            }
            // Creates a border around the new selection to show the user they've successfully
            // changed colors.
            newSelection.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
            // Sets the current selection to the new selection.
            this.currentSelection = newSelection;
        }
        // Updates the current color in the ChangeColorController so that now any grid squares
        // clicked will fill with the currently selected color tile's color.
        canvasGridPanel.getChangeColorController().setCurrentColor(currentSelection.getBackground());
    }

    public JButton getCurrentSelection() {
        return currentSelection;
    }

    /**
     * Changes the visual border indicating that a color is selected from the currently selected
     * color tile currentSelection to the new tile newSelection.
     * @param newSelection the new color tile to be indicated as selected.
     */
    public void changeSelectionIndicator(JButton newSelection) {
        this.currentSelection.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.currentSelection = newSelection;
        this.currentSelection.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }

    /**
     * Sends the color of the currently selected color tile to the ChangeColorController to
     * ensure that is the color that is being painted.
     */
    public void sendCurrentSelection() {
        canvasGridPanel.getChangeColorController().setCurrentColor(currentSelection.getBackground());
    }

}

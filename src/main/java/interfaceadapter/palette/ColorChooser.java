package interfaceadapter.palette;

import java.awt.*;

import javax.swing.*;

import interfaceadapter.canvasgrid.ChangeColorController;
import usecase.colorcanvas.PaletteSelection;


/**
 * This class displays the color chooser dialog to allow users to pick a color and update the palette.
 */

public class ColorChooser {
    /**
     * Opens the color chooser dialog.
     * When user selects a new color, updates the palette button's background color.
     *
     * @param parent is the parent component for dialog position
     * @param colorTile is the JButton for a pixel to color
     * @param paletteSelection is the paletteSelection instance managing the selection stage
     * @param changeColorController is the controller for updating current drawing color
     */
    public static void openColorChooserDialog(Component parent,
                                              JButton colorTile,
                                              PaletteSelection paletteSelection,
                                              ChangeColorController changeColorController) {
        final Color initialColor = colorTile.getBackground();
        final JColorChooser colorChooser = new JColorChooser(initialColor);

        final JDialog dialog = JColorChooser.createDialog(
                parent,
                "Choose your color.",
                true,
                colorChooser,
                actionEvent -> {
                    final Color selectedColor = colorChooser.getColor();
                    colorTile.setBackground(selectedColor);
                    if (paletteSelection.getCurrentSelection() == colorTile) {
                        paletteSelection.setCurrentSelection(colorTile);
                    }
                    if (changeColorController != null) {
                        changeColorController.setCurrentColor(selectedColor);
                    }
                },
                null
        );
        dialog.setVisible(true);
    }
}

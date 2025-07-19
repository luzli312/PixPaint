package interface_adapter.canvas_grid;

import usecase.ColorCanvasInterface;

import javax.swing.*;
import java.awt.*;

public class ChangeColorController {

    /**
     * Executes the Color Canvas Use Case.
     *
     * @param colorCode the username of the user logging in
     * @return
     */
    public void execute(String colorCode, JButton button) {
        Color newColor = Color.decode(colorCode);
        button.setBackground(newColor);
    }
}
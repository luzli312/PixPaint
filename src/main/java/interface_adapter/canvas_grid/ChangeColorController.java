package interface_adapter.canvas_grid;

import usecase.color_canvas.ColorCanvasInterface;

import javax.swing.*;
import java.awt.*;

public class ChangeColorController implements ColorCanvasInterface{

    private Color currentColor = Color.BLACK;

    /**
     * Executes the Color Canvas Use Case.
     *
     * @param button the grid square to be filled with the current color.
     * */
    public void execute(JButton button) {
        button.setBackground(currentColor);
    }

    public void setCurrentColor(Color newColor) {
        currentColor = newColor;
    }

}
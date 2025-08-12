package interface_adapter.canvas_grid;

import java.awt.Color;

import javax.swing.JButton;

import use_case.color_canvas.ColorCanvasInterface;

public class ChangeColorController implements ColorCanvasInterface {
    private static final Integer COLOR_NUM = 255;

    private Color currentColor = Color.BLACK;

    /**
     * Executes the Color Canvas Use Case.
     *
     * @param button the grid square to be filled with the current color.
     * */
    public void execute(JButton button) {
        if (currentColor.getAlpha() == 0) {
            button.setBackground(new Color(COLOR_NUM, COLOR_NUM, COLOR_NUM, COLOR_NUM));
            button.setName("transparent");
        }
        else {
            button.setBackground(currentColor);
            button.setName("Opaque");
        }
    }

    public void setCurrentColor(Color newColor) {
        currentColor = newColor;
    }

}

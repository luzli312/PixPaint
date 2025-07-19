package usecase;

import javax.swing.*;

public interface ColorCanvasInterface {
    /**
     * Executes the ColorCanvas use case.
     * @param colorCode button the input data
     */
    void execute(String colorCode, JButton button);
}

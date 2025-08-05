package usecase.export_canvas;

import java.awt.Color;
import java.util.Objects;

import javax.swing.JButton;

import view.CanvasGridPanel;

public class ExportCanvasInteractor {
    private static final Integer CANVAS_SIZE = 32;
    private static final Integer CELL_SIZE = 20;

    /**
     * Sets up the current canvas grid data to be exported as a png.
     * @param canvasGridPanel inputs the current canvas grid panel open.
     * @param title inputs the title of the current open project.
     */
    public void execute(CanvasGridPanel canvasGridPanel, String title) {
        final JButton[][] canvasData = canvasGridPanel.getAllCells();
        final Color[][] result = new Color[CANVAS_SIZE][CANVAS_SIZE];
        for (int i = 0; i < canvasData.length; i++) {
            for (int j = 0; j < canvasData[i].length; j++) {
                if (Objects.equals(canvasData[i][j].getName(), "transparent")) {
                    final Color transparent = new Color(255, 255, 255, 0);
                    result[i][j] = transparent;
                }
                else {
                    result[i][j] = canvasData[i][j].getBackground();
                }
            }
        }
        new ImageExporter().execute(result, CELL_SIZE, title);
    }
}


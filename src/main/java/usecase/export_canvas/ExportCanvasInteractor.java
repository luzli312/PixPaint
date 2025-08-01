package usecase.export_canvas;

import view.CanvasGridPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ExportCanvasInteractor {
    public void execute(CanvasGridPanel canvasGridPanel, String title) {
        JButton[][] canvasData = canvasGridPanel.getAllCells();
        Color[][] result = new Color[32][32];
        for (int i = 0; i < canvasData.length; i++) {
            for (int j = 0; j < canvasData[i].length; j++) {
                if (Objects.equals(canvasData[i][j].getName(), "transparent")) {
                    Color transparent = new Color(255, 255, 255, 0);
                    result[i][j] = transparent;
                }
                else {
                    result[i][j] = canvasData[i][j].getBackground();
                }
            }
        }
        new ImageExporter().execute(result, 20, title);
    }
}


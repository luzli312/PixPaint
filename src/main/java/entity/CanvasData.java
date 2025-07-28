package entity;

import org.bson.Document;
import view.CanvasGridPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CanvasData {

    private final User user;
    private final String projectTitle;
    private final CanvasGridPanel canvasGridPanel;
    private JButton[][] canvasData;

    public CanvasData(User user, String title, CanvasGridPanel canvasGridPanel) {
        this.user = user;
        this.projectTitle = title;
        this.canvasGridPanel = canvasGridPanel;
        this.canvasData = canvasGridPanel.getAllCells();
    }

    public void saveCanvasData() {
        this.canvasData = canvasGridPanel.getAllCells();
    }

    public String[][] formatCanvasData() {
        String[][] result = new String[32][32];
        for (int i = 0; i < canvasData.length; i++) {
            for (int j = 0; j < canvasData[i].length; j++) {
                result[i][j] = Integer.toString(canvasData[i][j].getBackground().getRGB());
            }
        }
        return result;
    }

    public Document exportCanvasData() {
        Document result = new Document("user", user.getUsername())
                .append("title", projectTitle);
        String[][] formattedData = this.formatCanvasData();
        for (int i = 0; i < formattedData.length; i++) {
            result.append(Integer.toString(i), Arrays.toString(formattedData[i]));
        }
        return result;
    }

}

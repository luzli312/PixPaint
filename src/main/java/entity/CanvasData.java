package entity;

import org.bson.Document;
import view.CanvasGridPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

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
                if (Objects.equals(canvasData[i][j].getName(), "transparent")) {
                    Color transparent = new Color(255, 255, 255, 0);
                    result[i][j] = Integer.toString(transparent.getRGB());
                    result[i][j].concat(Integer.toString(transparent.getAlpha()));
                }
                else {
                    result[i][j] = Integer.toString(canvasData[i][j].getBackground().getRGB());
                    result[i][j].concat(Integer.toString(1));
                }
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

    public static Color[][] parseCanvasData(Document rawCanvasData) {
        Color[][] result = new Color[32][32];
        for (int i = 0; i < 32; i++) {
            String row = rawCanvasData.getString(Integer.toString(i));
            String[] colorValues = row.substring(1, row.length() - 1).split(",");
            for (int j = 0; j < colorValues.length; j++) {
                String color = colorValues[j].trim();
                Color parsedColor = new Color(Integer.parseInt(color), true);
                result[i][j] = parsedColor;
            }
        }
        return result;
    }

}

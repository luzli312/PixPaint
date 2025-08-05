package entity;

import java.awt.Color;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.JButton;

import org.bson.Document;

import view.CanvasGridPanel;

public class CanvasData {
    private static final Integer SIZE = 32;

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

    /**
     * Saves the users Canvas Data to canvasData.
     */
    public void saveCanvasData() {
        this.canvasData = canvasGridPanel.getAllCells();
    }

    /**
     * Formats the canvas data to be ready to upload to database.
     * @return the formatted canvas data.
     */
    public String[][] formatCanvasData() {
        final String[][] result = new String[SIZE][SIZE];
        for (int i = 0; i < canvasData.length; i++) {
            for (int j = 0; j < canvasData[i].length; j++) {
                if (Objects.equals(canvasData[i][j].getName(), "transparent")) {
                    final Color transparent = new Color(255, 255, 255, 0);
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

    /**
     * Creates a Document to export the canvas data to the database.
     * @return a Document with username, password, formatted canvas data.
     */
    public Document exportCanvasData() {
        final Document result = new Document("user", user.getUsername())
                .append("title", projectTitle);
        final String[][] formattedData = this.formatCanvasData();
        for (int i = 0; i < formattedData.length; i++) {
            result.append(Integer.toString(i), Arrays.toString(formattedData[i]));
        }
        return result;
    }

    /**
     * Parses the Canvas grid data from the database to be usable for the rest of the program.
     * @param rawCanvasData inputs the data from the database directly.
     * @return the canvas grid data to be compatible/usable with the program.
     */
    public static Color[][] parseCanvasData(Document rawCanvasData) {
        final Color[][] result = new Color[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            final String row = rawCanvasData.getString(Integer.toString(i));
            final String[] colorValues = row.substring(1, row.length() - 1).split(",");
            for (int j = 0; j < colorValues.length; j++) {
                final String color = colorValues[j].trim();
                final Color parsedColor = new Color(Integer.parseInt(color), true);
                result[i][j] = parsedColor;
            }
        }
        return result;
    }

}

package usecase.export_canvas;

import view.ErrorSuccessView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageExporter {

    public void saveImage(BufferedImage image, String format, String filePath) {
        try {
            File outputFile = new File(filePath);
            ImageIO.write(image, format, outputFile);
            System.out.println("Image saved successfully to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
            e.printStackTrace();
            new ErrorSuccessView("Error", "Failed to export project: filepath doesn't exist");
        }
    }

    public void execute(Color[][] grid, int cellSize, String title) {
        int rows = 32;
        int cols = 32;

        int width = cols * cellSize;
        int height = rows * cellSize;

        String userHome = System.getProperty("user.home");
        String picturesPath = userHome + File.separator + File.separator + "Pictures" + File.separator + title + ".png";

        String filepath = JOptionPane.showInputDialog(null,
                "filepath:",
                "Export Project",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                 picturesPath).toString();


        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = image.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.setComposite(AlphaComposite.SrcOver);

        // Draw each cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Color color = grid[j][i];
                if (color != null && color.getAlpha() > 0) {
                    g2d.setColor(color);
                    g2d.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                }
            }
        }

        g2d.dispose();

        saveImage(image, "png", filepath);

    }

}
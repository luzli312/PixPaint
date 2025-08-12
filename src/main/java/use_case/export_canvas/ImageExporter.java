package use_case.export_canvas;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import view.ErrorSuccessView;

public class ImageExporter {

    /**
     * Saves the image of the canvas grid to computer.
     * @param image the BufferImage of the canvas grid.
     * @param format the file extension, png or jpeg
     * @param filePath the file path on computer, where the image will be saved.
     */
    public void saveImage(BufferedImage image, String format, String filePath) {
        try {
            final File outputFile = new File(filePath);
            ImageIO.write(image, format, outputFile);
            System.out.println("Image saved successfully to: " + filePath);
            new ErrorSuccessView("Success", "Image exported successfully to: " + filePath);
        }
        catch (IOException ex) {
            System.err.println("Error saving image: " + ex.getMessage());
            ex.printStackTrace();
            new ErrorSuccessView("Error", "Failed to export project: filepath doesn't exist");
        }
    }

    /**
     * Executes the save image function of the Export button.
     * @param grid the canvas grid data of the pixel art.
     * @param cellSize the size of each square of the grid.
     * @param title the title of the project.
     */
    public void execute(Color[][] grid, int cellSize, String title) {
        final int rows = 32;
        final int cols = 32;

        final int width = cols * cellSize;
        final int height = rows * cellSize;

        final String userHome = System.getProperty("user.home");
        final String picturesPath = userHome + File.separator + "Pictures" + File.separator + title + ".png";

        final String filepath = JOptionPane.showInputDialog(null,
                "filepath:",
                "Export Project",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                 picturesPath).toString();

        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        final Graphics2D g2d = image.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.setComposite(AlphaComposite.SrcOver);

        // Draw each cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                final Color color = grid[j][i];
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

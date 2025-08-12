package usecase.export_canvas;

import dataaccess.UserDataAccessObject;
import interfaceadapter.canvas_grid.ChangeColorController;
import org.junit.Test;
import usecase.login.LoginInteractor;
import view.CanvasGridPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ExportCanvasInteractorTest {

    @Test
    public void successTest() {
        new LoginInteractor("luzli", "mimi").execute();

        CanvasGridPanel canvasGridPanel = new CanvasGridPanel(new ChangeColorController());

        JButton gridSquare = canvasGridPanel.getAllCells()[0][0];

        gridSquare.doClick();
        assertEquals(Color.BLACK, gridSquare.getBackground());

        new ExportCanvasInteractor().execute(canvasGridPanel, "export_test");

        String userHome = System.getProperty("user.home");
        Path path = Paths.get(userHome, "Pictures", "export_test.png");
        File exportedImage = new File(path.toUri());

        assertTrue(exportedImage.exists());

        exportedImage.delete();
    }
}

package usecase.save_canvas;

import dataaccess.UserDataAccessObject;
import interfaceadapter.canvas_grid.ChangeColorController;
import interfaceadapter.export.ExportController;
import interfaceadapter.load.LoadController;
import interfaceadapter.save.SaveController;
import org.junit.Test;
import usecase.login.LoginInteractor;
import view.CanvasGridPanel;
import view.PalettePanel;
import view.PixelArtView;

import javax.swing.*;
import java.awt.*;

import static entity.CanvasData.parseCanvasData;
import static junit.framework.TestCase.assertEquals;

public class SaveCanvasInteractorTest {

    @Test
    public void successTest() {
        new LoginInteractor("luzli", "mimi").execute();

        CanvasGridPanel canvasGridPanel = new CanvasGridPanel(new ChangeColorController());
        PixelArtView pixelArtView = new PixelArtView("luzli");
        PalettePanel palettePanel = new PalettePanel(canvasGridPanel, new LoadController("luzli"),
                new ExportController("luzli"), pixelArtView);

        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

        JButton gridSquare = canvasGridPanel.getAllCells()[0][0];

        gridSquare.doClick();
        assertEquals(Color.BLACK, gridSquare.getBackground());

        new SaveController("save test").execute(pixelArtView, canvasGridPanel);

        Color savedColor = parseCanvasData(userDataAccessObject.getProject("luzli", "save test"))[0][0];

        assertEquals(Color.BLACK, savedColor);

        userDataAccessObject.deleteProject("luzli", "save test");
    }
}

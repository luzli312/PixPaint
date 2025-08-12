package usecase.loadcanvas;

import dataaccess.UserDataAccessObject;
import entity.CanvasData;
import interfaceadapter.canvasgrid.ChangeColorController;
import interfaceadapter.load.LoadController;
import interfaceadapter.loggedin.LoggedInState;
import org.junit.Test;
import usecase.login.LoginInteractor;
import view.CanvasGridPanel;
import view.PixelArtView;

import javax.swing.*;
import java.awt.*;

import static junit.framework.TestCase.assertEquals;

public class LoadCanvasInteractorTest {

    @Test
    public void successTest() {
        CanvasGridPanel canvasGridPanel = new PixelArtView("luzli").getCanvasPanel().getCanvasGridPanel();

        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

        JButton gridSquare = canvasGridPanel.getAllCells()[0][0];

        gridSquare.doClick();
        assertEquals(Color.BLACK, gridSquare.getBackground());

        CanvasData canvasData = new CanvasData("luzli", "load test",  canvasGridPanel);

        userDataAccessObject.createProject(canvasData.exportCanvasData());

        gridSquare.setBackground(Color.RED);
        assertEquals(Color.RED, gridSquare.getBackground());

        LoadCanvasInteractor loadCanvasInteractor = new LoadCanvasInteractor(userDataAccessObject);

        canvasGridPanel.loadCanvasGridPanel(
                new LoadController(loadCanvasInteractor).execute("luzli", "load test"));

        assertEquals(Color.BLACK, gridSquare.getBackground());

        userDataAccessObject.deleteProject("luzli", "load test");

    }
}

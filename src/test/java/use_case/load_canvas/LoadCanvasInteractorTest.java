package use_case.load_canvas;

import dataaccess.UserDataAccessObject;
import entity.CanvasData;
import interfaceadapter.canvas_grid.ChangeColorController;
import interfaceadapter.load.LoadController;
import interfaceadapter.logged_in.LoggedInState;
import org.junit.Test;
import usecase.login.LoginInteractor;
import view.CanvasGridPanel;

import javax.swing.*;
import java.awt.*;

import static junit.framework.TestCase.assertEquals;

public class LoadCanvasInteractorTest {

    @Test
    public void successTest() {
        new LoginInteractor("luzli", "mimi").execute();

        CanvasGridPanel canvasGridPanel = new CanvasGridPanel(new ChangeColorController());

        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

        JButton gridSquare = canvasGridPanel.getAllCells()[0][0];

        gridSquare.doClick();
        assertEquals(Color.BLACK, gridSquare.getBackground());

        CanvasData canvasData = new CanvasData(LoggedInState.getCurrentUser(), "load test",  canvasGridPanel);

        userDataAccessObject.createProject(canvasData.exportCanvasData());

        gridSquare.setBackground(Color.WHITE);
        assertEquals(Color.WHITE, gridSquare.getBackground());

        new LoadCanvasInteractor().execute("luzli", canvasGridPanel, "load test",
                new LoadController("luzli"));

        assertEquals(Color.BLACK, gridSquare.getBackground());

        userDataAccessObject.deleteProject("luzli", "load test");
    }
}

package usecase.save_canvas;

import dataaccess.UserDataAccessObject;
import interfaceadapter.canvas_grid.ChangeColorController;
import interfaceadapter.logged_in.LoggedInState;
import org.junit.Test;
import usecase.login.LoginInteractor;
import view.CanvasGridPanel;

import javax.swing.*;
import java.awt.*;

import static entity.CanvasData.parseCanvasData;
import static junit.framework.TestCase.assertEquals;

public class SaveCanvasInteractorTest {

    @Test
    public void successTest() {
        new LoginInteractor("luzli", "mimi").execute();

        CanvasGridPanel canvasGridPanel = new CanvasGridPanel(new ChangeColorController());

        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

        JButton gridSquare = canvasGridPanel.getAllCells()[0][0];

        gridSquare.doClick();
        assertEquals(Color.BLACK, gridSquare.getBackground());

        new SaveCanvasInteractor().execute(LoggedInState.getCurrentUser(), canvasGridPanel, "save test");

        Color savedColor = parseCanvasData(userDataAccessObject.getProject("luzli", "save test"))[0][0];

        assertEquals(Color.BLACK, savedColor);

        userDataAccessObject.deleteProject("luzli", "save test");
    }
}

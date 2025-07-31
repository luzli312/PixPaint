package usecase.save_canvas;

import data_access.UserDataAccessObject;
import entity.CanvasData;
import interface_adapter.logged_in.LoggedInState;
import entity.User;
import view.CanvasGridPanel;

import java.awt.*;

public class SaveCanvasInteractor {

    public void execute(User user, CanvasGridPanel canvasGridPanel, String title) {
        CanvasData canvasData = new CanvasData(user, title, canvasGridPanel);
        new UserDataAccessObject().createProject(canvasData.exportCanvasData());
    }
}

package usecase.save_canvas;

import data_access.UserDataAccessObject;
import entity.CanvasData;
import interface_adapter.logged_in.LoggedInState;
import view.CanvasGridPanel;

public class SaveCanvasInteractor {

    public void execute(CanvasGridPanel canvasGridPanel) {
        CanvasData test = new CanvasData(LoggedInState.getCurrentUser(), "test", canvasGridPanel);
        new UserDataAccessObject().createProject(test.exportCanvasData());
    }
}

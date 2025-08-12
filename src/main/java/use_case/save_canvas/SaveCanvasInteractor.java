package use_case.save_canvas;

import data_access.UserDataAccessObject;
import entity.CanvasData;
import entity.User;
import view.CanvasGridPanel;

public class SaveCanvasInteractor {
    /**
     * Executes the save function once clicking the save button.
     * @param user the username of the current user.
     * @param canvasGridPanel the data from the current canvas grid panel.
     * @param title the title of the project.
     */
    public void execute(User user, CanvasGridPanel canvasGridPanel, String title) {
        final CanvasData canvasData = new CanvasData(user, title, canvasGridPanel);
        new UserDataAccessObject().createProject(canvasData.exportCanvasData());
    }
}

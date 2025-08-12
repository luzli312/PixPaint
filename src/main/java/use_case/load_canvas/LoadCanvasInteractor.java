package use_case.load_canvas;

import java.awt.Color;

import org.bson.Document;

import data_access.UserDataAccessObject;
import entity.CanvasData;
import interface_adapter.load.LoadController;
import view.CanvasGridPanel;

public class LoadCanvasInteractor {
    /**
     * Loads the project from the database when clicking Ok in the load window.
     * @param username the users' username.
     * @param canvasGridPanel the current canvas grid panel thats open.
     * @param title the title of the project.
     * @param loadController the current controller of the load window.
     */
    public void execute(String username, CanvasGridPanel canvasGridPanel, String title, LoadController loadController) {
        loadController.setCurrentProject(title);
        final Document rawCanvasData = new UserDataAccessObject().getProject(username, title);
        final Color[][] parsedData = CanvasData.parseCanvasData(rawCanvasData);
        canvasGridPanel.loadCanvasGridPanel(parsedData);
    }
}

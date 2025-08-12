package usecase.loadcanvas;

import java.awt.Color;

import dataaccess.UserDataAccessObject;
import org.bson.Document;

import entity.CanvasData;
import interfaceadapter.load.LoadController;
import view.CanvasGridPanel;

public class LoadCanvasInteractor implements LoadInputBoundary {

    private final LoadDataAccessInterface dataAccessInterface;

    public LoadCanvasInteractor(LoadDataAccessInterface dataAccessInterface) {
        this.dataAccessInterface = dataAccessInterface;
    }

    /**
     * Loads the project from the database when clicking Ok in the load window.
     * @param loadInputData the data identifying the canvas to be loaded.
     */
    @Override
    public Color[][] execute(LoadInputData loadInputData) {
        final String username = loadInputData.getUsername();
        final String title = loadInputData.getProjectTitle();

        final Document rawCanvasData = dataAccessInterface.getProject(username, title);

        return CanvasData.parseCanvasData(rawCanvasData);
    }
}

package interfaceadapter.load;

import java.awt.*;

import usecase.loadcanvas.LoadInputBoundary;
import usecase.loadcanvas.LoadInputData;

public class LoadController {
    private final LoadInputBoundary loadCanvasInteractor;

    public LoadController(LoadInputBoundary loadCanvasInteractor) {
        this.loadCanvasInteractor = loadCanvasInteractor;
    }

    /**
     * Executes the load use case.
     * @param username the name of the user whose project is to be loaded.
     * @param projectTitle the name of the project to be loaded.
     */
    public Color[][] execute(String username, String projectTitle) {
        final LoadInputData loadInputData = new LoadInputData(username, projectTitle);
        return loadCanvasInteractor.execute(loadInputData);
    }

}

package usecase.loadcanvas;

import java.awt.*;

/**
 * Input boundary for actions related to loading.
 */
public interface LoadInputBoundary {

    /**
     * Executes the load use case.
     * @param loadInputData the data identifying the canvas to be loaded.
     * @return an array of the colors from the loaded canvas.
     */
    Color[][] execute(LoadInputData loadInputData);
}

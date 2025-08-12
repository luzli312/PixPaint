package usecase.loadcanvas;

import java.io.IOException;

import view.CanvasGridPanel;

public interface LoadInterface {

    /**
     * Executes the load use case.
     * @param canvasGridPanel the input data.
     * @throws IOException if access to database fails.
     */
    void execute(CanvasGridPanel canvasGridPanel) throws IOException;
}

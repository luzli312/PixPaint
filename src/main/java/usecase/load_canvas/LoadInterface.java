package usecase.load_canvas;

import view.CanvasGridPanel;

import java.io.IOException;

public interface LoadInterface {

    /**
     * Executes the load use case.
     * @param canvasGridPanel the input data.
     */
    void execute(CanvasGridPanel canvasGridPanel) throws IOException;
}

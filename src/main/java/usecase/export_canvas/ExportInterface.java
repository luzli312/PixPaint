package usecase.export_canvas;

import view.CanvasGridPanel;


public interface ExportInterface {
    /**
     * Executes the Export use case.
     * @param canvasGridPanel and title the input data
     */
    void execute(CanvasGridPanel canvasGridPanel, String title);
}

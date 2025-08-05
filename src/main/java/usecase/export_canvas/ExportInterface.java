package usecase.export_canvas;

import view.CanvasGridPanel;

public interface ExportInterface {
    /**
     * Executes the Export use case.
     * @param canvasGridPanel the current canvas grid panel that is open.
     * @param title the title of the current open project.
     */
    void execute(CanvasGridPanel canvasGridPanel, String title);
}

package interface_adapter.export;

import usecase.export_canvas.ExportCanvasInteractor;
import usecase.export_canvas.ExportInterface;
import view.CanvasGridPanel;

public class ExportController implements ExportInterface {
    private final String username;

    public ExportController(String username) {
        this.username = username;
    }

    /**
     * Executes the Export Use Case to open the load window.
     * @param canvasGridPanel inputs the current canvas grid panel open.
     * @param title inputs the name of the project.
     */
    public void execute(CanvasGridPanel canvasGridPanel, String title) {
        new ExportCanvasInteractor().execute(canvasGridPanel, title);
    }
}

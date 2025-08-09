package usecase.save_canvas;

import view.CanvasGridPanel;
import view.PixelArtView;

public interface SaveInterface {
    /**
     * Executes the save use case, to save the current project onto the database.
     * @param pixelArtView the main application with the canvas grid and tool panel.
     * @param canvasGridPanel the current canvas grid panel.
     */
    void execute(PixelArtView pixelArtView, CanvasGridPanel canvasGridPanel);
}

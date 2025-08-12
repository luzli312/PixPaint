package interfaceadapter.load;

import java.io.IOException;

import usecase.loadcanvas.LoadInterface;
import view.CanvasGridPanel;
import view.LoadView;

public class LoadController implements LoadInterface {
    private final String username;
    private String projectTitle;

    public LoadController(String username) {
        this.projectTitle = username + "_Project";
        this.username = username;
    }

    /**
     * Executes the Load Use Case to open the load window.
     * @param canvasGridPanel inputs the current canvas grid panel open.
     * @throws IOException from creating LoadView.
     */
    public void execute(CanvasGridPanel canvasGridPanel) throws IOException {
        new LoadView(username, canvasGridPanel, this);
    }

    public void setCurrentProject(String title) {
        this.projectTitle = title;
    }

    public String getCurrentProject() {
        return projectTitle;
    }
}

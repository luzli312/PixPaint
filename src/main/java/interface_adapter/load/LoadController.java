package interface_adapter.load;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JPanel;

import usecase.load_canvas.LoadInterface;
import view.CanvasGridPanel;
import view.LoadView;

public class LoadController implements LoadInterface {
    private final JPanel main = new JPanel(new BorderLayout());
    private final CardLayout cardLayout = new CardLayout();
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
        new LoadView(username, main, cardLayout, canvasGridPanel, this);
    }

    public void setCurrentProject(String title) {
        this.projectTitle = title;
    }

    public String getCurrentProject() {
        return projectTitle;
    }
}

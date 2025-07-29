package interface_adapter.load;

import view.CanvasGridPanel;
import view.LoadView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoadController {
    private final JPanel main = new JPanel(new BorderLayout());
    private final CardLayout cardLayout = new CardLayout();
    private final String username;

    public LoadController(String username) {
        this.username = username;
    }

    /**
     * Executes the Load Use Case to open the load window
     *
     */
    public void execute(CanvasGridPanel canvasGridPanel) throws IOException {
        new LoadView(username, main, cardLayout, canvasGridPanel);
    }
}

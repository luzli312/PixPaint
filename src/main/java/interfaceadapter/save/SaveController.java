package interfaceadapter.save;

import javax.swing.JOptionPane;

import com.mongodb.MongoException;
import dataaccess.UserDataAccessObject;
import entity.CanvasData;
import entity.User;
import interfaceadapter.loggedin.LoggedInState;
import usecase.savecanvas.SaveCanvasInteractor;
import usecase.savecanvas.SaveInterface;
import view.CanvasGridPanel;
import view.ErrorSuccessView;
import view.PixelArtView;

public class SaveController implements SaveInterface {

    private final String projectName;

    public SaveController(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Executes the save use case, to save the current project onto the database.
     *
     * @param pixelArtView    the main application with the canvas grid and tool panel.
     * @param canvasGridPanel the current canvas grid panel.
     */
    public void execute(PixelArtView pixelArtView, CanvasGridPanel canvasGridPanel) {

        final UserDataAccessObject userDataAccessObject = new UserDataAccessObject();
        final User currentUser = LoggedInState.getCurrentUser();

        if (projectName == null || projectName.trim().isEmpty()) {
            new ErrorSuccessView("Error", "Empty project name!");
        }
        try {
            if (userDataAccessObject.projectExists(currentUser, projectName)) {
                final int option = JOptionPane.showConfirmDialog(pixelArtView,
                        "Project already exists. Overwrite?", "Overwrite",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    final CanvasData canvasData = new CanvasData(currentUser.getUsername(), projectName, canvasGridPanel);
                    userDataAccessObject.updateProject(canvasData.exportCanvasData());
                    new ErrorSuccessView("Success", "Project updated successfully!");
                }
            }
            else {
                new SaveCanvasInteractor().execute(currentUser, canvasGridPanel, projectName);
                new ErrorSuccessView("Success", "Project saved successfully!");
            }
        }
        catch (MongoException ex) {
            new ErrorSuccessView("Error", "Failed to save project.");
        }
    }
}

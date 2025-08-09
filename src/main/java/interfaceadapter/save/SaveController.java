package interfaceadapter.save;

import java.awt.HeadlessException;

import javax.swing.JOptionPane;

import dataaccess.UserDataAccessObject;
import entity.CanvasData;
import entity.User;
import interfaceadapter.logged_in.LoggedInState;
import usecase.save_canvas.SaveCanvasInteractor;
import usecase.save_canvas.SaveInterface;
import view.CanvasGridPanel;
import view.ErrorSuccessView;
import view.PixelArtView;

public class SaveController implements SaveInterface {
    /**
     * Executes the save use case, to save the current project onto the database.
     * @param pixelArtView the main application with the canvas grid and tool panel.
     * @param canvasGridPanel the current canvas grid panel.
     */
    public void execute(PixelArtView pixelArtView, CanvasGridPanel canvasGridPanel) {
        final String projectName = JOptionPane.showInputDialog(
                pixelArtView, "Enter project name:", "Save Project", JOptionPane.PLAIN_MESSAGE);

        if (projectName == null || projectName.trim().isEmpty()) {
            new ErrorSuccessView("Error", "Empty project name!");
        }

        final UserDataAccessObject userDataAccessObject = new UserDataAccessObject();
        final User currentUser = LoggedInState.getCurrentUser();

        try {
            if (userDataAccessObject.projectExists(currentUser, projectName)) {
                final int option = JOptionPane.showConfirmDialog(pixelArtView,
                        "Project already exists. Overwrite?", "Overwrite",
                        JOptionPane.YES_NO_OPTION);
                if (option != JOptionPane.YES_OPTION) {
                    return;
                }
                final CanvasData canvasData = new CanvasData(currentUser, projectName, canvasGridPanel);
                userDataAccessObject.updateProject(canvasData.exportCanvasData());
                new ErrorSuccessView("Success", "Project updated successfully!");
            }
            else {
                new SaveCanvasInteractor().execute(currentUser, canvasGridPanel, projectName);
                new ErrorSuccessView("Success", "Project saved successfully!");
            }
        }
        catch (HeadlessException ex) {
            new ErrorSuccessView("Error", "Failed to save project: " + ex.getMessage());
        }
    }
}

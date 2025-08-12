package usecase.savecanvas;

import org.bson.Document;

/**
 * DAO for the save use case.
 */
public interface SaveDataAccessInterface {

    /**
     * Creates the project in the database.
     * @param canvasData inputs the users Canvas Data.
     */
    void createProject(Document canvasData);
}

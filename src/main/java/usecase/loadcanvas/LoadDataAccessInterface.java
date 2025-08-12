package usecase.loadcanvas;

import org.bson.Document;

/**
 * DAO for the load use case.
 */
public interface LoadDataAccessInterface {

    /**
     * Gets the users project from the database.
     * @param username inputs the users username.
     * @param title inputs the project title of the users project.
     * @return returns the project matching the title and username.
     */
    Document getProject(String username, String title);
}

package usecase.loadcanvas;

/**
 * The input data for the login use case.
 */
public class LoadInputData {

    private final String projectTitle;
    private final String username;

    public LoadInputData(String username, String projectTitle) {
        this.username = username;
        this.projectTitle = projectTitle;
    }

    public String getUsername() {
        return username;
    }

    public String getProjectTitle() {
        return projectTitle;
    }
}

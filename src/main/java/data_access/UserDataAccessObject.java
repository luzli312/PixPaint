package data_access;

import java.io.IOException;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.User;

public class UserDataAccessObject {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String TITLE = "title";

    /**
     * Creates a user in the database.
     * @param username inputs the users username.
     * @param password inputs the users password.
     */
    public void createUser(String username, String password) {
        final Document user = new Document(USERNAME, username)
                .append(PASSWORD, password);

        Config.USERS.insertOne(user);
    }

    /**
     * Checks if a user already exists in the database with the same username.
     * @param username inputs the users username.
     * @return true if a user already exists and false if not.
     */
    public boolean existsByName(String username) {
        final Document result = Config.USERS.find(Filters.eq(USERNAME, username)).first();
        return result != null;
    }

    /**
     * Checks if the inputted password matches with the password for an existing user
     * in the database.
     * @param username the username of the account being logged into.
     * @param password the user inputted password for the account.
     * @return true if the password is correct and false otherwise.
     */
    public boolean passwordCorrect(String username, String password) {
        final Document result = Config.USERS.find(Filters.eq(USERNAME, username)).first();
        return password.equals(result.getString(PASSWORD));
    }

    /**
     * Returns the user from the database.
     * @param username inputs the users username.
     * @return a User with the name and password from the database.
     */
    public User getUser(String username) {
        final Document result = Config.USERS.find(Filters.eq(USERNAME, username)).first();
        final String name = result.getString(USERNAME);
        final String password = result.getString(PASSWORD);
        return new User(name, password);
    }

    /**
     * Creates the project in the database.
     * @param canvasData inputs the users Canvas Data.
     */
    public void createProject(Document canvasData) {
        Config.PROJECTS.insertOne(canvasData);
    }

    /**
     * Gets the users project from the database.
     * @param username inputs the users username.
     * @param projectTitle inputs the project title of the users project.
     * @return returns the project matching the title and username.
     */
    public Document getProject(String username, String projectTitle) {
        final Bson filter = Filters.and(Filters.eq(USER, username), Filters.eq(TITLE, projectTitle));
        return Config.PROJECTS.find(filter).first();
    }

    /**
     * Gets all the users projects names.
     * @param username inputs the users username.
     * @return all the users project titles/
     * @throws IOException thrown is not able to open database.
     */
    public static ArrayList<String> getProjectNames(String username) throws IOException {
        final ArrayList<String> projects = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(Config.getApiToken())) {
            final MongoDatabase database = mongoClient.getDatabase("PixPaint");
            final MongoCollection<Document> collection = database.getCollection("projects");

            for (Document project : collection.find(Filters.eq(USER, username))) {
                projects.add(project.get(TITLE).toString());
            }
        }
        return projects;
    }

    /**
     * Checks if the project already exists in the database.
     * @param user inputs the users username.
     * @param projectTitle inputs the projects current title.
     * @return the project with the inputted title if exists otherwise null.
     */
    public boolean projectExists(User user, String projectTitle) {
        final Document found = Config.PROJECTS.find(
                Filters.and(
                        Filters.eq(USER, user.getUsername()),
                        Filters.eq(TITLE, projectTitle)
                )).first();
        return found != null;
    }

    /**
     * Will overwrite/update the project in the database.
     * @param canvasData the users current Canvas Data.
     */
    public void updateProject(Document canvasData) {
        Config.PROJECTS.replaceOne(
                Filters.and(
                        Filters.eq(USER, canvasData.getString(USER)),
                        Filters.eq(TITLE, canvasData.getString(TITLE))
                ),
                canvasData);
    }

    /**
     * Removes the corresponding user from the database.
     * @param username the name of the user to be removed.
     */
    public void deleteUser(String username) {
        Config.USERS.deleteOne(Filters.eq(USERNAME, username));
    }

    /**
     * Removes the corresponding user's project from the database.
     * @param username the name of the user.
     * @param projectTitle the name of the project to be deleted.
     */
    public void deleteProject(String username, String projectTitle) {
        Config.PROJECTS.deleteOne(Filters.and(Filters.eq(USERNAME, username), Filters.eq(TITLE, projectTitle)));
    }

    /**
     * Counts all users with the corresponding username.
     * The return value should be at most 1 if the program is working correctly.
     * @param username of the users to be matched.
     * @return the number of users with this username.
     */
    public long numberUsers(String username) {
        return Config.USERS.countDocuments(Filters.eq(USERNAME, username));
    }
}

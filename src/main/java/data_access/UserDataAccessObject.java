package data_access;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entity.User;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Filters;

public class UserDataAccessObject {
    /*
    private final String username;
    private final String password;

    public UserDataAccessObject (String username, String password) {
        this.username = username;
        this.password = password;
    }
    */
    public void createUser(String username, String password) {
        Document user = new Document("username", username)
                .append("password", password);

        Config.users.insertOne(user);
    }

    /**
     * Checks if a user already exists in the database with the same username.
     * @return true if a user already exists and false if not.
     */
    public boolean existsByName(String username) {
        Document result = Config.users.find(eq("username", username)).first();
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
        Document result = Config.users.find(eq("username", username)).first();
        assert result != null;
        return password.equals(result.getString("password"));
    }

    public User getUser(String username) {
        Document result = Config.users.find(eq("username", username)).first();
        assert result != null;
        final String name = result.getString("username");
        final String password = result.getString("password");
        return new User(name, password);
    }

    public void createProject(Document canvasData) {
        Config.projects.insertOne(canvasData);
    }

    public Document getProject(String username, String projectTitle) {
        Bson filter = Filters.and(eq("user", username), eq("title", projectTitle));
        return Config.projects.find(filter).first();
    }

    public static ArrayList<String> getProjectNames(String username) throws IOException {
        ArrayList<String> projects = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(Config.getAPIToken())) {
            MongoDatabase database = mongoClient.getDatabase("PixPaint");
            MongoCollection<Document> collection = database.getCollection("projects");

            for (Document project : collection.find(eq("user", username))) {
                projects.add(project.get("title").toString());
            }
        }
        return projects;
    }

    public boolean projectExists(User user, String projectTitle) {
        Document found = Config.projects.find(
                Filters.and(
                        Filters.eq("user", user.getUsername()),
                        Filters.eq("title", projectTitle)
                )).first();
        return found != null;
    }

    public void updateProject(Document canvasData) {
        Config.projects.replaceOne(
                Filters.and(
                        Filters.eq("user", canvasData.getString("user")),
                        Filters.eq("title", canvasData.getString("title"))
                ),
                canvasData);
    }
}

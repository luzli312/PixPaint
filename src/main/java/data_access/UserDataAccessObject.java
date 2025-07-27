package data_access;

import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class UserDataAccessObject {
    /*
    private final String username;
    private final String password;

    public UserDataAccessObject (String username, String password) {
        this.username = username;
        this.password = password;
    }
    */

    public void create(String username, String password) {
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

}

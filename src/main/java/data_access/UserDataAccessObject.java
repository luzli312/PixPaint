package data_access;

import entity.User;
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

    public User get (String username) {
        Document result = Config.users.find(eq("username", username)).first();
        assert result != null;
        final String name = result.getString("username");
        final String password = result.getString("password");
        return new User(name, password);
    }
}

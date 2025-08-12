package usecase.signup;

/**
 * DAO for the signup use case.
 */
public interface SignupDataAccessInterface {

    /**
     * Creates a user in the database.
     * @param username inputs the users username.
     * @param password inputs the users password.
     */
    void createUser(String username, String password);

    /**
     * Checks if a user already exists in the database with the same username.
     * @param username inputs the users username.
     * @return true if a user already exists and false if not.
     */
    boolean existsByName(String username);
}

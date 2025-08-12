package usecase.login;

import entity.User;

/**
 * DAO for the login use case.
 */
public interface LoginDataAccessInterface {

    /**
     * Checks if a user already exists in the database with the same username.
     * @param username inputs the users username.
     * @return true if a user already exists and false if not.
     */
    boolean existsByName(String username);

    /**
     * Checks if the inputted password matches with the password for an existing user
     * in the database.
     * @param username the username of the account being logged into.
     * @param password the user inputted password for the account.
     * @return true if the password is correct and false otherwise.
     */
    boolean passwordCorrect(String username, String password);

    /**
     * Returns the user from the database.
     * @param username inputs the users username.
     * @return a User with the name and password from the database.
     */
    User getUser(String username);
}

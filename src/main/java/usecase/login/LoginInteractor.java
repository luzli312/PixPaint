package usecase.login;

import dataaccess.UserDataAccessObject;
import interfaceadapter.logged_in.LoggedInState;
import view.ErrorSuccessView;

public class LoginInteractor {

    private final LoginInputData input;
    private final UserDataAccessObject userDataAccessObject;

    public LoginInteractor(String username, String password) {
        this.input = new LoginInputData(username, password);
        this.userDataAccessObject = new UserDataAccessObject();
    }

    /**
     * Executes the different windows to open once clicking the login button.
     * Depends on the username, password, and success case.
     */
    public void execute() {
        if (!userDataAccessObject.existsByName(input.getUsername().trim())) {
            new ErrorSuccessView("Error", "No such user exists.");
        }
        else if (!userDataAccessObject.passwordCorrect(input.getUsername().trim(), input.getPassword().trim())) {
            new ErrorSuccessView("Error", "Incorrect password.");
        }
        else {
            LoggedInState.setCurrentUser(userDataAccessObject.getUser(input.getUsername().trim()));
            LoggedInState.execute();
        }
    }

}

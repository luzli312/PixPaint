package usecase.login;

import data_access.UserDataAccessObject;
import interface_adapter.logged_in.LoggedInState;
import view.ErrorSuccessView;

public class LoginInteractor {

    private final LoginInputData input;
    private final UserDataAccessObject userDataAccessObject;

    public LoginInteractor(LoginInputData input) {
        this.input = input;
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

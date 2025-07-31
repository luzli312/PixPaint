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

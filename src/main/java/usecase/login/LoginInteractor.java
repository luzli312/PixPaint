package usecase.login;

import data_access.UserDataAccessObject;
import entity.User;
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
        if (!userDataAccessObject.existsByName(input.getUsername())) {
            new ErrorSuccessView("Error", "No such user exists.");
        }
        else if (!userDataAccessObject.passwordCorrect(input.getUsername(), input.getPassword())) {
            new ErrorSuccessView("Error", "Incorrect password.");
        }
        else {
            LoggedInState.setCurrentUser(userDataAccessObject.get(input.getUsername()));
            LoggedInState.execute();
        }
    }

}

package usecase.signup;

import data_access.UserDataAccessObject;
import view.ErrorSuccessView;

public class SignupInteractor {

    private final SignupInputData input;
    private final UserDataAccessObject userDataAccessObject;

    public SignupInteractor(SignupInputData input) {
        this.input = input;
        this.userDataAccessObject = new UserDataAccessObject();
    }

    public void execute() {

        if (input.getUsername().isEmpty() || input.getPassword().isEmpty()) {
            new ErrorSuccessView("Error", "Username and/or password contains no characters.");
        }
        else if (input.getUsername().matches(".* .*") || input.getPassword().matches(".* .*")) {
            new ErrorSuccessView("Error", "Cannot have whitespace characters in username or password");
        }
        else if (userDataAccessObject.existsByName(input.getUsername())) {
            new ErrorSuccessView("Error", "A user with this username already exists.");
        }
        else if (!input.getPassword().equals(input.getPasswordConfirm())) {
            new ErrorSuccessView("Error", "The passwords do not match.");
        }
        else {
            userDataAccessObject.createUser(input.getUsername(), input.getPassword());
            new ErrorSuccessView("Success", "You have successfully signed up for PixPaint!");
        }
    }

}

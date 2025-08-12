package use_case.signup;

import data_access.UserDataAccessObject;
import view.ErrorSuccessView;

public class SignupInteractor {
    private static final String ERROR = "Error";

    private final SignupInputData input;
    private final UserDataAccessObject userDataAccessObject;

    public SignupInteractor(String username, String password, String passwordConfirmation) {
        this.input = new SignupInputData(username, password, passwordConfirmation);
        this.userDataAccessObject = new UserDataAccessObject();
    }

    /**
     * Executes the different windows once clicking the signup button.
     * Depending on the input of the signup window.
     */
    public void execute() {

        if (input.getUsername().isEmpty() || input.getPassword().isEmpty()) {
            new ErrorSuccessView(ERROR, "Username and/or password contains no characters.");
        }
        else if (input.getUsername().matches(".* .*") || input.getPassword().matches(".* .*")) {
            new ErrorSuccessView(ERROR, "Cannot have whitespace characters in username or password");
        }
        else if (userDataAccessObject.existsByName(input.getUsername())) {
            new ErrorSuccessView(ERROR, "A user with this username already exists.");
        }
        else if (!input.getPassword().equals(input.getPasswordConfirm())) {
            new ErrorSuccessView(ERROR, "The passwords do not match.");
        }
        else {
            userDataAccessObject.createUser(input.getUsername(), input.getPassword());
            new ErrorSuccessView("Success", "You have successfully signed up for PixPaint!");
        }
    }

}

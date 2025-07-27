package usecase.signup;

import view.ErrorView;

public class SignupInteractor {

    private final SignupInputData input;

    public SignupInteractor(SignupInputData input) {
        this.input = input;
    }

    public void execute() {
        /**
        if (userDataAccessObject.existsByName(input.getUsername())) {
            new ErrorView("User Already Exists", "A user with this username already exists.");
        }
         **/
        if (!input.getPassword().equals(input.getPasswordConfirm())) {
            new ErrorView("Password Confirmation Failed", "The passwords do not match.");
        }
    }

}

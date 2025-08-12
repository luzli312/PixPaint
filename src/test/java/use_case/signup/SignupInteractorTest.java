package use_case.signup;

import dataaccess.UserDataAccessObject;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class SignupInteractorTest {

    @Test
    public void successTest() {
        SignupInputData signupInputData = new SignupInputData("success", "pass", "pass");

        SignupInteractor signupInteractor = new SignupInteractor(signupInputData.getUsername(),
                signupInputData.getPassword(), signupInputData.getPasswordConfirm());

        signupInteractor.execute();

        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

        assertTrue(userDataAccessObject.existsByName("success"));
        assertNotNull(userDataAccessObject.getUser("success"));
        assertEquals("success", userDataAccessObject.getUser("success").getUsername());
        assertEquals("pass", userDataAccessObject.getUser("success").getPassword());

        userDataAccessObject.deleteUser("success");
    }

    @Test
    public void fieldEmptyTest() {
        SignupInputData noUsernameInputData = new SignupInputData("", "pass", "pass");

        SignupInteractor noUsernameInteractor = new SignupInteractor(noUsernameInputData.getUsername(),
                noUsernameInputData.getPassword(), noUsernameInputData.getPasswordConfirm());

        noUsernameInteractor.execute();

        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

        assertFalse(userDataAccessObject.existsByName(""));

        SignupInputData noPasswordInputData = new SignupInputData("user", "", "");

        SignupInteractor noPasswordInteractor = new SignupInteractor(noPasswordInputData.getUsername(),
                noPasswordInputData.getPassword(), noPasswordInputData.getPasswordConfirm());

        noPasswordInteractor.execute();

        assertFalse(userDataAccessObject.existsByName("user"));
    }

    @Test
    public void spaceInFieldsTest() {
        SignupInputData userSpaceInputData = new SignupInputData("hi world", "pass", "pass");

        SignupInteractor userSpaceInteractor = new SignupInteractor(userSpaceInputData.getUsername(),
                userSpaceInputData.getPassword(), userSpaceInputData.getPasswordConfirm());

        userSpaceInteractor.execute();

        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

        assertFalse(userDataAccessObject.existsByName("hi world"));

        SignupInputData passwordSpaceInputData = new SignupInputData("user", "p word", "p word");

        SignupInteractor passwordSpaceInteractor = new SignupInteractor(passwordSpaceInputData.getUsername(),
                passwordSpaceInputData.getPassword(), passwordSpaceInputData.getPasswordConfirm());

        passwordSpaceInteractor.execute();

        assertFalse(userDataAccessObject.existsByName("user"));
    }

    @Test
    public void userAlreadyExistsTest() {
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

        assertTrue(userDataAccessObject.existsByName("luzli"));

        SignupInputData signupInputData = new SignupInputData("luzli", "pass", "pass");

        SignupInteractor signupInteractor = new SignupInteractor(signupInputData.getUsername(),
                signupInputData.getPassword(), signupInputData.getPasswordConfirm());

        signupInteractor.execute();

        assert(userDataAccessObject.numberUsers("luzli") == 1);
    }

    @Test
    public void differentPasswordsTest() {
        SignupInputData signupInputData = new SignupInputData("user", "pass", "password");

        SignupInteractor signupInteractor = new SignupInteractor(signupInputData.getUsername(),
                signupInputData.getPassword(), signupInputData.getPasswordConfirm());

        signupInteractor.execute();

        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

        assertFalse(userDataAccessObject.existsByName("user"));
    }

}

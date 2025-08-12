package usecase.login;

import dataaccess.UserDataAccessObject;
import interfaceadapter.loggedin.LoggedInState;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class LoginInteractorTest {

    @Test
    public void successTest() {
        LoginInputData inputData = new LoginInputData("test", "password");
        UserDataAccessObject userDataAccessObject = new UserDataAccessObject();
        userDataAccessObject.createUser(inputData.getUsername(), inputData.getPassword());

        LoginInteractor loginInteractor = new LoginInteractor(inputData.getUsername(), inputData.getPassword());
        loginInteractor.execute();

        assertEquals("test", LoggedInState.getCurrentUser().getUsername());
        assertEquals("password", LoggedInState.getCurrentUser().getPassword());

        LoggedInState.logout();
        userDataAccessObject.deleteUser("test");
}

    @Test
    public void wrongPasswordTest() {
        LoginInputData inputData = new LoginInputData("luzli", "wrong");

        LoginInteractor loginInteractor = new LoginInteractor(inputData.getUsername(), inputData.getPassword());
        loginInteractor.execute();

        assertNull(LoggedInState.getCurrentUser());
    }

    @Test
    public void nonexistentUserTest() {
        LoginInputData inputData = new LoginInputData("Test", "password");

        LoginInteractor loginInteractor = new LoginInteractor(inputData.getUsername(), inputData.getPassword());
        loginInteractor.execute();

        assertNull(LoggedInState.getCurrentUser());
    }

}

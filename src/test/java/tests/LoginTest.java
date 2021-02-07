package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTest extends BaseTest {
    public static final String EMPTY_FIELD = "";
    public static final String ERROR_LOGIN = "error_login";
    public static final String ERROR_PASSWORD = "error_password";

    @Test
    public void emptyFieldTest() {
        loginPage.openPage();
        loginPage.login(EMPTY_FIELD, EMPTY_FIELD);
        loginPage.waitMessageErrorDisplayed();
        String message = loginPage.getErrorMessage();
        Assert.assertEquals(message, ERROR_MESSAGE_EMPTY);
    }

    @Test
    public void emptyPasswordTest() {
        loginPage.openPage();
        loginPage.login(USERNAME, EMPTY_FIELD);
        loginPage.waitMessageErrorDisplayed();
        String message = loginPage.getErrorMessage();
        Assert.assertEquals(message, ERROR_MESSAGE_EMPTY_PASSWORD);
    }

    @Test
    public void verificationLoginTest() {
        loginPage.openPage();
        List<String> logins = loginPage.getListLogin();
        for (String login : logins) {
            loginPage.login(login, PASSWORD);
            Assert.assertTrue(productsPage.isPageOpened());
            //open the login page to check the next user
            loginPage.openPage().waitMessageErrorDisplayed();
        }
    }

    @Test
    public void checkingMessageIncorrectLogin() {
        loginPage.openPage();
        loginPage.login(ERROR_LOGIN, PASSWORD);
        loginPage.waitMessageErrorDisplayed();
        String message = loginPage.getErrorMessage();
        Assert.assertEquals(message, ERROR_MESSAGE_LOGIN_OR_PASSWORD);
    }

    @Test
    public void checkingMessageIncorrectPassword() {
        loginPage.openPage();
        loginPage.login(USERNAME, ERROR_PASSWORD);
        loginPage.waitMessageErrorDisplayed();
        String message = loginPage.getErrorMessage();
        Assert.assertEquals(message, ERROR_MESSAGE_LOGIN_OR_PASSWORD);
    }

    @Test
    public void closeErrorMessageTest() {
        loginPage.openPage();
        loginPage.login(USERNAME, ERROR_PASSWORD);
        loginPage.waitMessageErrorDisplayed();
        if (loginPage.isShowErrorMessage()) {
            loginPage.closeErrorMessage();
        }
        Assert.assertFalse(loginPage.isShowErrorMessage());
    }
}

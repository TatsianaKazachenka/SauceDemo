package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTest extends BaseTest {
    public static final String EMPTY_FIELD = "";
    public static final String ERROR_LOGIN = "error_login";
    public static final String ERROR_PASSWORD = "error_password";

    @Test
    @Description("Validation login with empty credits")
    public void emptyFieldTest() {
        loginSteps.login(EMPTY_FIELD, EMPTY_FIELD);
        String message = loginPage.getErrorMessage();
        Assert.assertEquals(message, ERROR_MESSAGE_EMPTY);
    }

    @Test
    @Description("Validation login with empty password")
    public void emptyPasswordTest() {
        loginSteps.login(USERNAME, EMPTY_FIELD);
        String message = loginPage.getErrorMessage();
        Assert.assertEquals(message, ERROR_MESSAGE_EMPTY_PASSWORD);
    }

    @Test
    @Description("Login verification with all test credits")
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
    @Description("Validation login with empty password")
    public void checkingMessageIncorrectLogin() {
        loginSteps.login(ERROR_LOGIN, PASSWORD);
        String message = loginPage.getErrorMessage();
        Assert.assertEquals(message, ERROR_MESSAGE_LOGIN_OR_PASSWORD);
    }

    @Test
    @Description("Checking the login with an invalid login")
    public void checkingMessageIncorrectPassword() {
        loginSteps.login(USERNAME, ERROR_PASSWORD);
        String message = loginPage.getErrorMessage();
        Assert.assertEquals(message, ERROR_MESSAGE_LOGIN_OR_PASSWORD);
    }

    @Test
    @Description("Checking if the error message is closed")
    public void closeErrorMessageTest() {
        loginSteps.login(USERNAME, ERROR_PASSWORD);
        if (loginPage.isShowErrorMessage()) {
            loginPage.closeErrorMessage();
        }
        Assert.assertFalse(loginPage.isShowErrorMessage());
    }
}

package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;

import static org.jsoup.helper.Validate.fail;

public class LoginPage extends BasePage<LoginPage> {

    @FindBy(xpath = "//*[@id='user-name']")
    WebElement usernameInput;
    @FindBy(xpath = "//*[@id='password']")
    WebElement passwordInput;
    @FindBy(id = "login-button")
    WebElement loginBtn;
    @FindBy(xpath = "//*[@id='login_credentials']")
    WebElement listLogin;
    @FindBy(xpath = "//*[@id='login_button_container']//*[@data-test='error']")
    WebElement errorMessage;
    @FindBy(xpath = "//*[@id='login_button_container']//button[@class='error-button']")
    WebElement errorMessageCloseBtn;
    @FindBy(xpath = "//*[@id='login_button_container']")
    WebElement loginContent;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    LoginPage waitForPageOpened() {
        return this;
    }

    /**
     * Login to the site
     *
     * @param username
     * @param password
     */
    @Step("Login")
    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    /**
     * Open login page
     */
    public LoginPage openPage() {
        load();
        isLoaded();
        return this;
    }

    public List<String> getListLogin() {
        return Arrays.asList(listLogin.getText()
                .replace("Accepted usernames are:\n", "").split("\n"));
    }

    @Step("Getting an error message")
    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void waitMessageErrorDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
    }

    public void closeErrorMessage() {
        errorMessageCloseBtn.click();
    }

    public boolean isShowErrorMessage() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUsernamePasswordFieldsEmpty() {
        try {
            String name = usernameInput.getText();
            String password = passwordInput.getText();
            return name.isEmpty() && password.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void load() {
        openPage(BASE_URL + LOGIN_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            loginContent.isDisplayed();
        } catch (NoSuchElementException e) {
            fail("Cannot locator");
        }
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageFactory extends BasePage {
    public LoginPageFactory(WebDriver driver) {
        super(driver);
    }

    @Override
    BasePage waitForPageOpened() {
        return null;
    }

    @FindBy(xpath="//*[@data-test='username']")
    WebElement usernameInput;
    @FindBy(xpath="//*[@data-test='password']")
    WebElement userpasswordInput;

    @FindBy(id = "login-button")
    WebElement loginBtn;

    /**
     * Login to the site
     *
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        userpasswordInput.sendKeys(password);
        loginBtn.click();
    }

    /**
     * Open login page
     */
    public void openPage() {
        String loginUrl = BASE_URL  + LOGIN_URL;
        driver.get(loginUrl);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {

    }
}

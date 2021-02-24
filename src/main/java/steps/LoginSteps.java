package steps;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginSteps {
    private LoginPage page;

    public LoginSteps(WebDriver driver) {
        page = new LoginPage(driver);
    }

    public LoginSteps login(String username, String password){
        page.openPage();
        page.login(username, password);
        page.waitMessageErrorDisplayed();
        return this;
    }
}

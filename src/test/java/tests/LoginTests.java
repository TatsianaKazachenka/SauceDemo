package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Constants.LoginTestsConctants;

public class LoginTests extends BaseTest {
    public static final By ERROR_MESSAGE_EMPTY = By.xpath("//*[@id='login_button_container']//h3[@data-test='error']");

    @Test
    public void emptyFieldTest() {
        loginPage.openPage();
        loginPage.login("", "");

        String message = driver.findElement(ERROR_MESSAGE_EMPTY).getText();
        Assert.assertEquals(message, LoginTestsConctants.ERROR_MESSAGE_EMPTY);
    }
}

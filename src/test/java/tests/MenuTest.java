package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MenuTest extends BaseTest {

    @Test
    @Description("Checking if the side menu is open")
    public void openMenuTest() {
        menuSteps.openPage();
        Assert.assertTrue(menuPage.isShowMenu());
    }

    @Test(description = "checking click all item")
    public void clickAllItemTest() {
        menuSteps.clickHref("allItem");
        Assert.assertTrue(productsPage.isPageOpened());
    }

    @Test(description = "checking click about item")
    public void clickAboutTest() {
        menuSteps.clickHref("about");
        Assert.assertTrue(menuPage.isWaitForAboutPageOpened());
    }

    @Test(description = "checking click logout")
    public void clickLogoutTest() {
        menuSteps.clickHref("logout");
        Assert.assertTrue(loginPage.isUsernamePasswordFieldsEmpty());
    }

    @Test
    @Description("Side menu close check")
    public void closeMenuTest() {
        menuSteps.openPage()
                .clickCloseBtn();
        Assert.assertTrue(menuPage.isNotShowMenu());
    }
}

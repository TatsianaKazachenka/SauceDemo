package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MenuTest extends BaseTest {

    @Test
    public void openMenuTest() {
        menuPage.openPage().waitForPageOpened();
        Assert.assertTrue(menuPage.isShowMenu());
    }

    @Test
    public void clickAllItemTest() {
        menuPage.openPage()
                .waitForPageOpened()
                .clickHref("allItem");
        Assert.assertTrue(productsPage.isPageOpened());
    }

    @Test
    public void clickAboutTest() {
        menuPage.openPage()
                .waitForPageOpened()
                .clickHref("about");
        Assert.assertTrue(menuPage.isWaitForAboutPageOpened());
    }

    @Test
    public void clickLogoutTest() {
        menuPage.openPage()
                .waitForPageOpened()
                .clickHref("logout");
        Assert.assertTrue(loginPage.isUsernamePasswordFieldsEmpty());
    }

    @Test
    public void closeMenuTest() {
        menuPage.openPage()
                .waitForPageOpened()
                .clickCloseBtn();
        Assert.assertTrue(menuPage.isNotShowMenu());
    }
}

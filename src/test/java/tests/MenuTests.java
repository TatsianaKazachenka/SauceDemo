package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MenuTests extends BaseTests {

    @Test
    public void openMenuTest() {
        menuPage.openPage().waitForPageOpened();
        Assert.assertTrue(menuPage.isShowMenu());
    }

    @Test
    public void clickAllItemTest() {
        menuPage.openPage().waitForPageOpened();
        menuPage.clickHref("allItem");
        Assert.assertTrue(productsPage.isPageOpened());
    }

    @Test
    public void clickAboutTest() {
        menuPage.openPage().waitForPageOpened();
        menuPage.clickHref("about");
        Assert.assertTrue(menuPage.isWaitForAboutPageOpened());
    }

    @Test
    public void clickLogoutTest() {
        menuPage.openPage().waitForPageOpened();
        menuPage.clickHref("logout");
        Assert.assertTrue(loginPage.isEmptyField());
    }

    @Test
    public void closeMenuTest(){
        menuPage.openPage().waitForPageOpened();
        menuPage.clickCloseBtn().isWaitForAboutPageOpened();
        Assert.assertFalse(menuPage.isShowMenu());
    }
}

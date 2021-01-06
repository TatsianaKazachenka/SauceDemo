package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTests {
    @Test
    public void removeItemCartTest() {
        productsPage.openPage().waitForPageOpened();
        productsPage.clickAddOrRemoveProductItem(PRODUCT_NAME, true);
        cartPage.openPage().waitForPageOpened();
        cartPage.clickRemoveBtn();
        Assert.assertFalse(cartPage.isEmptyCart());
    }

    @Test
    public void continueShoppingTest() {
        cartPage.openPage().waitForPageOpened();
        cartPage.clickContinueShopping();
        Assert.assertTrue(productsPage.isPageOpened());
    }
}

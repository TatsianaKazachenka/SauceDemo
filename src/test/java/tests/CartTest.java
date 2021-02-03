package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    @Test
    public void removeItemCartTest() {
        productsPage.openPage()
                .waitForPageOpened()
                .clickAddOrRemoveProductItem(PRODUCT_NAME, true);
        cartPage.openPage()
                .waitForPageOpened()
                .clickRemoveBtn();
        Assert.assertFalse(cartPage.isEmptyCart());
    }

    @Test
    public void continueShoppingTest() {
        cartPage.openPage()
                .waitForPageOpened()
                .clickContinueShopping();
        Assert.assertTrue(productsPage.isPageOpened());
    }
}

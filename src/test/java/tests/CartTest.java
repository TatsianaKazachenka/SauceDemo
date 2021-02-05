package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    @Test
    @Description("Check for removal of an item from the cart")
    public void removeItemCartTest() {
        productsSteps.openPageAndClickAddOrRemoveItem(PRODUCT_NAME, true);
        cartSteps.openPage()
                .clickRemoveBtn();
        Assert.assertFalse(cartPage.isEmptyCart());
    }

    @Test
    @Description("Checking the continue button")
    public void continueShoppingTest() {
        cartSteps.openPage()
                .clickContinueShopping();
        Assert.assertTrue(productsPage.isPageOpened());
    }
}

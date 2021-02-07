package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class ProductsTest extends BaseTest {
    public static final String PRODUCT_TYPE_SORT = "az";

    @Test
    @Description("Checking if an item was added to the cart")
    public void addProductToCartTest() {
        productsSteps.openPageAndClickAddOrRemoveItem(PRODUCT_NAME, true);

        String price_on_product = productsPage.getProductPrice(PRODUCT_NAME);
        cartPage.openPage();

        String price_on_cart = cartPage.isCheckedProduct(PRODUCT_NAME) ?
                cartPage.getCartItemPrice(PRODUCT_NAME) : PRODUCT_NOT_FOUND;
        String price_on_quantity = cartPage.isCheckedProduct(PRODUCT_NAME) ?
                cartPage.getCartItemQuantity(PRODUCT_NAME) : PRODUCT_NOT_FOUND;

        Assert.assertEquals(price_on_cart, price_on_product);
        Assert.assertEquals(price_on_quantity, "1");
    }

    @Test
    @Description("Checking the removal of an item from the cart")
    public void removeProductFromCartTest() {
        productsSteps.openPageAndClickAddOrRemoveItem(PRODUCT_NAME, true);

        cartPage.openPage();
        boolean isCheckedProduct = cartPage.isCheckedProduct(PRODUCT_NAME);
        if (isCheckedProduct) {
            driver.navigate().back();
            productsPage.clickAddOrRemoveProductItem(PRODUCT_NAME, false);
            cartPage.openPage();
        }
        Assert.assertTrue(isCheckedProduct);
    }

    @Test
    @Description("Checking the number of items in the cart on the badge")
    public void checkedAddProductToCartBadgeTest() {
        productsSteps.openPageAndClickAddOrRemoveItem(PRODUCT_NAME, true);

        String count = productsPage.getProductCountToBadge();
        Assert.assertEquals(count, "1");
    }

    @Test
    @Description("Checking the sorting of goods")
    public void sortProductsTest() {
        productsSteps.openPage();
        productsPage.clickSortProducts(PRODUCT_TYPE_SORT);
        List<WebElement> elements = productsPage.getProductElements();
        ArrayList<String> elementsText = new ArrayList<String>();
        for (WebElement element : elements) {
            elementsText.add(element.getText());
        }
        ArrayList<String> sortElementsText = productsPage.sortCollection(elementsText);
        Assert.assertEquals(elementsText, sortElementsText);
    }
}

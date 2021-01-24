package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class ProductsTests extends BaseTests {
    public static final String PRODUCT_TYPE_SORT = "az";

    @Test
    public void addProductToCartTest() {
        productsPage.openPage().waitForPageOpened();
        productsPage.clickAddOrRemoveProductItem(PRODUCT_NAME, true);

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
    public void removeProductFromCartTest() {
        productsPage.openPage().waitForPageOpened();
        productsPage.clickAddOrRemoveProductItem(PRODUCT_NAME, true);

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
    public void checkedAddProductToCartBadgeTest() {
        productsPage.openPage().waitForPageOpened();
        productsPage.clickAddOrRemoveProductItem(PRODUCT_NAME, true);

        String count = productsPage.getProductCountToBadge();
        Assert.assertEquals(count, "1");
    }

    @Test
    public void mappingItemProductsToCartTest() {
        productsPage.openPage().waitForPageOpened();
        int count = productsPage.getProductElements().size();
        for (int i = 0; i < count; i++) {
            List<WebElement> elements = productsPage.getProductElements();
            String nameProduct = elements.get(i).getText();
            productsPage.clickAddOrRemoveProductItem(nameProduct, true);
            cartPage.openPage();
            String nameProductCart = cartPage.getCartItemName();
            Assert.assertEquals(nameProduct, nameProductCart);
            driver.navigate().back();
        }
    }

    @Test
    public void sortAtoZProductsTest() {
        productsPage.openPage().waitForPageOpened();
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

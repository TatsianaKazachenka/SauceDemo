package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class ProductsTests extends BaseTests {
    public static final String PRODUCT_TYPE_SORT = "az";

    @Test
    public void addProductToCartTest() {
        login();
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
        login();
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
        login();
        productsPage.clickAddOrRemoveProductItem(PRODUCT_NAME, true);

        String count = productsPage.getProductCountToBadge();
        Assert.assertEquals(count, "1");
    }

    @Test
    public void mappingItemProductsToCartTest() {
        login();
        int count = initElements().size();
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
        login();
        productsPage.clickSortProducts(PRODUCT_TYPE_SORT);
        List<WebElement> elements = productsPage.getProductElements();
        ArrayList<String> elementsText = new ArrayList<String>();
        for (WebElement element : elements) {
            elementsText.add(element.getText());
        }
        ArrayList<String> sortElementsText = elementsText;
        Collections.sort(sortElementsText, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.toString().compareTo(s2.toString());
            }
        });
        Assert.assertEquals(elementsText, sortElementsText);
    }

    public void login() {
        loginPage.openPage().waitMessageErrorDisplayed();
        loginPage.login(USERNAME, PASSWORD);
    }

    public List<WebElement> initElements() {
        return productsPage.getProductElements();
    }
}

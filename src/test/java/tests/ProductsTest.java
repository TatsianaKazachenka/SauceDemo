package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Constants.LoginPageConstants;
import tests.Constants.ProductsTestConstants;

import java.util.*;

public class ProductsTest extends BaseTest {
    public static final String ADD_PRODUCT_CLASS = "btn_primary";
    public static final String REMOVE_PRODUCT_CLASS = "btn_secondary";
    public static final String PRODUCT_TYPE_SORT = "az";

    @Test
    public void addProductToCartTest() {
        login();
        productsPage.clickProductItem(ProductsTestConstants.PRODUCT_NAME, ADD_PRODUCT_CLASS);

        String price_on_product = productsPage.getProductPrice(ProductsTestConstants.PRODUCT_NAME);
        cartPage.openPage();

        String price_on_cart = cartPage.isCheckedProduct(ProductsTestConstants.PRODUCT_NAME) ?
                cartPage.getCartItemPrice(ProductsTestConstants.PRODUCT_NAME) : ProductsTestConstants.PRODUCT_NOT_FOUND;
        String price_on_quantity = cartPage.isCheckedProduct(ProductsTestConstants.PRODUCT_NAME) ?
                cartPage.getCartItemQuantity(ProductsTestConstants.PRODUCT_NAME) : ProductsTestConstants.PRODUCT_NOT_FOUND;

        Assert.assertEquals(price_on_cart, price_on_product);
        Assert.assertEquals(price_on_quantity, "1");
    }

    @Test
    public void removeProductFromCartTest() {
        login();
        productsPage.clickProductItem(ProductsTestConstants.PRODUCT_NAME, ADD_PRODUCT_CLASS);

        cartPage.openPage();
        boolean isCheckedProduct = cartPage.isCheckedProduct(ProductsTestConstants.PRODUCT_NAME);
        if (isCheckedProduct) {
            driver.navigate().back();
            productsPage.clickProductItem(ProductsTestConstants.PRODUCT_NAME, REMOVE_PRODUCT_CLASS);
            cartPage.openPage();
        }
        Assert.assertTrue(isCheckedProduct);
    }

    @Test
    public void checkedAddProductToCartBadgeTest() {
        login();
        productsPage.clickProductItem(ProductsTestConstants.PRODUCT_NAME, ADD_PRODUCT_CLASS);

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
            productsPage.clickProductItem(nameProduct, ADD_PRODUCT_CLASS);
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
        loginPage.openPage();
        loginPage.login(LoginPageConstants.USERNAME, LoginPageConstants.PASSWORD);
    }

    public List<WebElement> initElements() {
        return productsPage.getProductElements();
    }
}

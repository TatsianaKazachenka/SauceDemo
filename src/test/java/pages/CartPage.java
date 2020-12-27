package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.Constants.CartPageConstants;

import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver, String url) {
        super(driver, url);
    }

    public static final String CART_ITEM = "//*[text() = '%s']/ancestor::*[@class='cart_item']";
    public static final By CART_ITEMS = By.xpath("//*[@class='cart_list']//*[@class='cart_item']//*[@class='inventory_item_name']");
    public static final By CART_ITEM_PRICE = By.xpath("//*[@class='inventory_item_price']");
    public static final By CART_ITEM_QUANTITY = By.xpath("//*[@class='cart_quantity']");
    public static final By CART_ITEM_NAME = By.xpath("//*[@id='cart_contents_container']//*[@class='cart_item']//*[@class='inventory_item_name']");

    /**
     * Open card page
     */
    public void openPage() {
        String cartUrl = url + CartPageConstants.URL;
        driver.get(cartUrl);
    }

    /**
     * receiving product in the basket
     *
     * @param productName
     * @return
     */
    public WebElement getCartItem(String productName) {
        return driver.findElement(By.xpath(String.format(CART_ITEM, productName)));
    }

    /**
     * checking the availability of goods by name in the cart
     *
     * @param productName
     * @return
     */
    public Boolean isCheckedProduct(String productName) {
        List<WebElement> elements = driver.findElements(CART_ITEMS);
        boolean isPoduct = false;
        for (WebElement element : elements) {
            isPoduct = element.getText().equals(productName);
            if (isPoduct) continue;
        }
        return isPoduct;
    }

    /**
     * getting the price of an item in the cart
     *
     * @param productName
     * @return
     */
    public String getCartItemPrice(String productName) {
        return getCartItem(productName)
                .findElement(CART_ITEM_PRICE).getText();
    }

    /**
     * getting the quantity of an item in the cart
     *
     * @param productName
     * @return
     */
    public String getCartItemQuantity(String productName) {
        return getCartItem(productName)
                .findElement(CART_ITEM_QUANTITY).getText();
    }

    /**
     * getting the name of an item in the cart
     *
     * @return
     */
    public String getCartItemName() {
        return driver
                .findElement(CART_ITEM_NAME).getText();
    }
}

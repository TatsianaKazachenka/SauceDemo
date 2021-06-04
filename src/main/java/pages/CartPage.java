package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

import java.util.List;

public class CartPage extends BasePage {
    public static final String CART_ITEM = "//*[text() = '%s']/ancestor::*[@class='cart_item']";
    public static final By CART_ITEM_PRICE = By.xpath("//*[@class='inventory_item_price']");
    public static final By CART_ITEM_QUANTITY = By.xpath("//*[@class='cart_quantity']");
    public static final By CHECKOUT_BUTTON = By.xpath("//*[@class = 'btn_action checkout_button']");

    @FindBy(xpath = "//*[@class='cart_list']//*[@class='cart_item']//*[@class='inventory_item_name']")
    List<WebElement> cartItems;
    @FindBy(xpath = "//*[@id='cart_contents_container']//*[@class='cart_item']//*[@class='inventory_item_name']")
    WebElement cartItemName;
    @FindBy(xpath = "//*[@id='cart_contents_container']//button[contains(@class, 'cart_button')]")
    WebElement cartRemoveBtn;
    @FindBy(xpath = "//*[@class='cart_list']//*[@class='cart_item']")
    WebElement cartEmptyItems;
    @FindBy(xpath = "//*[@id='cart_contents_container']//a[@class='btn_secondary']")
    WebElement btnContinueShopping;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartPage waitForPageOpened() {
        isLoaded();
        return this;
    }

    /**
     * Open card page
     */
    @Step("Opening Cart")
    public CartPage openPage() {
        load();
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    /**
     * receiving product in the basket
     *
     * @param productName
     * @return
     */
    @Step("Receiving product '{productName}' in the cart")
    public WebElement getCartItem(String productName) {
        return driver.findElement(By.xpath(String.format(CART_ITEM, productName)));
    }

    /**
     * checking the availability of goods by name in the cart
     *
     * @param productName
     * @return
     */
    @Step("Checking the availability of goods by name in the cart")
    public Boolean isCheckedProduct(String productName) {
        List<WebElement> elements = cartItems;
        boolean isPoduct = false;
        for (WebElement element : elements) {
            isPoduct = element.getText().equals(productName);
        }
        return isPoduct;
    }

    /**
     * getting the price of an item in the cart
     *
     * @param productName
     * @return
     */
    @Step("Getting the price of an item in the cart")
    public String getCartItemPrice(String productName) {
        AllureUtils.takeScreenshot(driver);
        return getCartItem(productName)
                .findElement(CART_ITEM_PRICE).getText();
    }

    /**
     * getting the quantity of an item in the cart
     *
     * @param productName
     * @return
     */
    @Step("Getting the quantity of an item in the cart")
    public String getCartItemQuantity(String productName) {
        AllureUtils.takeScreenshot(driver);
        return getCartItem(productName)
                .findElement(CART_ITEM_QUANTITY).getText();
    }

    /**
     * getting the name of an item in the cart
     *
     * @return
     */
    @Step("Removing a product from the cart")
    public String getCartItemName() {
        return cartItemName.getText();
    }

    public CartPage clickRemoveBtn() {
        cartRemoveBtn.click();
        return this;
    }

    @Step("Check for full cart")
    public boolean isEmptyCart() {
        try {
            return cartEmptyItems.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public CartPage clickContinueShopping() {
        btnContinueShopping.click();
        return this;
    }

    @Override
    protected void load() {
        openPage(BASE_URL + CART_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));
    }
}

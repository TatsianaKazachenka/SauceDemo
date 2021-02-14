package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.AllureUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.jsoup.helper.Validate.fail;

@Log4j2
public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public static final String PRODUCT_NOT_FOUND = "not found";

    public static final String PRODUCT_ITEM = "//*[text() = '%s']/ancestor::*[@class='inventory_item']";
    public static final String PRODUCT_TO_CART_BTN = "//button[contains(@class, '%s')]";
    public static final By PRODUCT_PRICE = By.xpath("//*[@class='inventory_item_price']");
    public static final By PRODUCT_LABEL = By.xpath("//*[contains(text(),'Product')]");
    public static final String PRODUCT_ADD_TO_CART_BTN = "//button[contains(@class, 'btn_primary')]";
    public static final String PRODUCT_REMOVE_FROM_CART_BTN = "//button[contains(@class, 'btn_secondary')]";

    @FindBy(xpath = "//*[@id='inventory_filter_container']/select[@class='product_sort_container']")
    WebElement productSort;
    @FindBy(xpath = "//*[@id='shopping_cart_container']//*[contains(@class, 'shopping_cart_badge')]")
    WebElement productCountBadge;
    @FindBy(xpath = "//*[@class='inventory_list']//*[@class='inventory_item']//*[@class='inventory_item_name']")
    List<WebElement> productsItem;

    /**
     * get the product by name
     *
     * @param productName
     * @return web element product
     */
    public WebElement getProduct(String productName) {
        log.info("get the product by name " + productName);
        try {
        return driver.findElement(By.xpath(String.format(PRODUCT_ITEM, productName)));
        }
        catch (Exception ex){
            AllureUtils.takeScreenshot(driver);
        }
        return null;
    }

    /**
     * click on the item product
     *
     * @param productName
     * @param isAddToCart
     */
    @Step("Adding or removing product '{productName}' to cart")
    public ProductsPage clickAddOrRemoveProductItem(String productName, boolean isAddToCart) {
        if (isAddToCart) {
            log.info(String.format("Adding product '%s' to cart", productName));
            getProduct(productName).findElement(By.xpath(PRODUCT_ADD_TO_CART_BTN)).click();
        } else {
            log.info(String.format("Removing product '%s' to cart", productName));
            getProduct(productName).findElement(By.xpath(PRODUCT_REMOVE_FROM_CART_BTN)).click();
        }
        return this;
    }

    /**
     * get the product price by name
     *
     * @param productName
     * @return product price
     */
    public String getProductPrice(String productName) {
        log.info("get the product price by name " + productName);
        try {
            return getProduct(productName)
                    .findElement(PRODUCT_PRICE).getText()
                    .replace("$", "")
                    .trim();
        }
        catch (Exception ex){
            AllureUtils.takeScreenshot(driver);
        }
        return PRODUCT_NOT_FOUND;
    }

    /**
     * get the count product badge
     *
     * @return count
     */
    @Step("Get the count product badge")
    public String getProductCountToBadge() {
        return productCountBadge.getText().trim();
    }

    /**
     * get all products
     *
     * @return
     */
    public List<WebElement> getProductElements() {
        return productsItem;
    }

    /**
     * sorting the product list
     *
     * @param typeSort
     */
    @Step("Sorting the product list")
    public void clickSortProducts(String typeSort) {
        Select select = new Select(productSort);
        select.selectByValue(typeSort);
    }

    @Override
    public ProductsPage waitForPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_LABEL));
        } catch (TimeoutException exception) {
            Assert.fail(String.format("Products page in not loaded! Locator: '%s' was not found!", PRODUCT_LABEL));
        }
        return this;
    }

    @Step("Opening Products")
    public ProductsPage openPage() {
        load();
        isLoaded();
        return this;
    }

    public boolean isPageOpened() {
        try {
            return driver.findElement(PRODUCT_LABEL).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<String> sortCollection(ArrayList<String> sortElementsText) {
        Collections.sort(sortElementsText, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.toString().compareTo(s2.toString());
            }
        });
        return sortElementsText;
    }

    @Override
    protected void load() {
        openPage(BASE_URL + PRODUCTS_URL);
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            driver.findElement(PRODUCT_LABEL).isDisplayed();
        } catch (NoSuchElementException e) {
            fail("Cannot locator");
        }
    }
}

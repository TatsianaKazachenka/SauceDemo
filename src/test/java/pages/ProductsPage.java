package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver, String url) {
        super(driver, url);
    }

    public static final String PRODUCT_ITEM = "//*[text() = '%s']/ancestor::*[@class='inventory_item']";
    public static final String PRODUCT_TO_CART_BTN = "//button[contains(@class, '%s')]";
    public static final String PRODUCT_SORT = "//*[@id='inventory_filter_container']/select[@class='product_sort_container']";
    public static final By PRODUCT_PRICE = By.xpath("//*[@class='inventory_item_price']");
    public static final By PRODUCT_COUNT_BADGE = By.xpath("//*[@id='shopping_cart_container']//*[contains(@class, 'shopping_cart_badge')]");
    public static final By PRODUCTS_ITEM = By.xpath("//*[@class='inventory_list']//*[@class='inventory_item']//*[@class='inventory_item_name']");

    /**
     * get the product by name
     *
     * @param productName
     * @return web element product
     */
    public WebElement getProduct(String productName) {
        return driver.findElement(By.xpath(String.format(PRODUCT_ITEM, productName)));
    }

    /**
     * click on the item product
     *
     * @param productName
     * @param nameClick
     */
    public void clickProductItem(String productName, String nameClick) {
        getProduct(productName)
                .findElement(By.xpath(String.format(PRODUCT_TO_CART_BTN, nameClick))).click();
    }

    /**
     * get the product price by name
     *
     * @param productName
     * @return product price
     */
    public String getProductPrice(String productName) {
        return getProduct(productName)
                .findElement(PRODUCT_PRICE).getText().replace("$", "").trim();
    }

    /**
     * get the count product badge
     *
     * @return count
     */
    public String getProductCountToBadge() {
        return driver.findElement(PRODUCT_COUNT_BADGE).getText().trim();
    }

    /**
     * get all products
     *
     * @return
     */
    public List<WebElement> getProductElements() {
        return driver.findElements(PRODUCTS_ITEM);
    }

    /**
     * sorting the product list
     *
     * @param typeSort
     */
    public void clickSortProducts(String typeSort) {
        Select select = new Select(driver.findElement(By.xpath(PRODUCT_SORT)));
        select.selectByValue(typeSort);
    }
}

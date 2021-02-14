package steps;

import org.openqa.selenium.WebDriver;
import pages.ProductsPage;

public class ProductsSteps {
    private ProductsPage page;

    public ProductsSteps(WebDriver driver){
        page = new ProductsPage(driver);
    }

    public ProductsSteps openPageAndClickAddOrRemoveItem(String productName, boolean isAddToCart){
        openPage();
        page.clickAddOrRemoveProductItem(productName, isAddToCart);
        return this;
    }

    public ProductsSteps openPage(){
        page.openPage().waitForPageOpened();
        return this;
    }
}

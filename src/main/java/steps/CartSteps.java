package steps;

import org.openqa.selenium.WebDriver;
import pages.CartPage;

public class CartSteps {
    private CartPage page;

    public CartSteps(WebDriver driver) {
        page = new CartPage(driver);
    }

    public CartPage openPage(){
        page.openPage().waitForPageOpened();
        return page;
    }
}

package steps;

import org.openqa.selenium.WebDriver;
import pages.MenuPage;

public class MenuSteps {
    private MenuPage page;

    public MenuSteps(WebDriver driver) {
        page = new MenuPage(driver);
    }

    public MenuPage openPage(){
        page.openPage().waitForPageOpened();
        return page;
    }

    public void clickHref(String item) {
        openPage().clickHref(item);
    }
}

package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.jsoup.helper.Validate.fail;

@Log4j2
public class MenuPage extends BasePage {
    public MenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='menu_button_container']//*[@class='bm-burger-button']/button")
    WebElement openMenuBtn;
    @FindBy(xpath = "//*[@id='menu_button_container']//*[@class='bm-menu']")
    WebElement contentMenu;
    @FindBy(xpath = "//*[@id='headerMainNav']//*[@class='nav-image']")
    WebElement aboutPageLogo;
    @FindBy(id = "inventory_sidebar_link")
    WebElement hrefAllItems;
    @FindBy(id = "about_sidebar_link")
    WebElement hrefAbout;
    @FindBy(id = "logout_sidebar_link")
    WebElement hrefLogout;
    @FindBy(xpath = "//*[@id='menu_button_container']//*[contains(@class, 'button')]/button")
    WebElement closeMenuBtn;

    @Step("Opening Menu")
    public MenuPage openPage() {
        load();
        return this;
    }

    public MenuPage waitForPageOpened() {
        isLoaded();
        return this;
    }

    public boolean isWaitForAboutPageOpened() {
        try {
            return aboutPageLogo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Check for menu appearance")
    public boolean isShowMenu() {
        log.info("Show menu");
        try {
            return contentMenu.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNotShowMenu() {
        try {
            return contentMenu.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Сhecking the following menu links")
    public MenuPage clickHref(String type) {
        log.info("Сhecking the following menu links " + type);
        WebElement element = null;
        switch (type) {
            case "allItem":
                element = hrefAllItems;
                break;
            case "about":
                element = hrefAbout;
                break;
            case "logout":
                element = hrefLogout;
                break;
            default:
                throw new IllegalArgumentException(String.format("There is not such type '%s' !", type));
        }
        element.click();
        return this;
    }

    @Step("Close menu")
    public MenuPage clickCloseBtn() {
        log.info("Close menu");
        closeMenuBtn.click();
        return this;
    }

    @Override
    protected void load() {
        openPage(BASE_URL + PRODUCTS_URL);
        openMenuBtn.click();
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            contentMenu.isDisplayed();
        } catch (NoSuchElementException e) {
            fail("Cannot locator");
        }
    }
}

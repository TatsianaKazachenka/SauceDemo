package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.jsoup.helper.Validate.fail;

public class MenuPage extends BasePage{
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

    public boolean isShowMenu() {
        try {
            return contentMenu.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickHref(String type){
        WebElement element = null;
        switch (type){
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
                break;
        }
        element.click();
    }

    public MenuPage clickCloseBtn(){
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

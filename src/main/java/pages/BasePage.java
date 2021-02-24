package pages;

import constants.IConstants;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
abstract class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T> implements IConstants {
    WebDriver driver;
    WebDriverWait wait;

    BasePage(WebDriver driver) {
        this.driver = driver;
        int timeout = 20;
        wait = new WebDriverWait(driver, timeout);
        log.debug("Implicit timeout = " + timeout);
        PageFactory.initElements(driver, this);
    }

    abstract BasePage waitForPageOpened();

    public void openPage(String url) {
        log.info("Open URL = " + url);
        driver.get(url);
    }
}

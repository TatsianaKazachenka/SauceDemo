package pages;

import org.openqa.selenium.WebDriver;

abstract class BasePage {
    WebDriver driver;
    String url;

    BasePage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

}

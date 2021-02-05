package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CartPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.ProductsPage;
import constants.ITestConstants;
import steps.CartSteps;
import steps.LoginSteps;
import steps.MenuSteps;
import steps.ProductsSteps;

import java.util.concurrent.TimeUnit;

public class BaseTest implements ITestConstants {
    WebDriver driver;

    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    MenuPage menuPage;

    ProductsSteps productsSteps;
    MenuSteps menuSteps;
    LoginSteps loginSteps;
    CartSteps cartSteps;

    @BeforeMethod
    public void initTest() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-incognito");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        initPages();
        initSteps();
    }

    @AfterMethod(alwaysRun = true)
    public void endTest() {
        driver.quit();
    }

    private void initPages() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        menuPage = new MenuPage(driver);
    }

    private void initSteps(){
        productsSteps = new ProductsSteps(driver);
        menuSteps = new MenuSteps(driver);
        loginSteps = new LoginSteps(driver);
        cartSteps = new CartSteps(driver);
    }
}

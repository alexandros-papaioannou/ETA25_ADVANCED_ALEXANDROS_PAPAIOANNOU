package ui.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ui.support.browser.BrowserFactory;
import ui.support.browser.WebDriverManager;
import support.logging.LoggerUtility;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new BrowserFactory().getBrowserOptions();
        LoggerUtility.infoLog("###### The browser was opened successfully ######");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverManager.closeDriver();
        LoggerUtility.infoLog("###### The browser was closed successfully ######");
    }
}

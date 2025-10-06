package ui.tests;

import org.openqa.selenium.WebDriver;
import ui.support.browser.BrowserFactory;
import ui.support.browser.WebDriverManager;
import ui.support.utils.LoggerUtility;

public class BaseTest {

    protected WebDriver driver;


    public void setUp() {
        driver = new BrowserFactory().getBrowserOptions();
        LoggerUtility.infoLog("###### The browser was opened successfully ######");
    }


    public void tearDown() {
        WebDriverManager.closeDriver();
        LoggerUtility.infoLog("###### The browser was closed successfully ######");
    }
}

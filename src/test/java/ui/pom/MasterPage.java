package ui.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class MasterPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(id = "Layer_1")
    protected WebElement webshopLogo;

    @FindBy(xpath = "//img[@alt='Banner']")
    protected WebElement webshopBanner;

    @FindBy(css = "ul[aria-label='Main menu'] > li")
    protected List<WebElement> menuItems;

    @FindBy(xpath = "//button[@id='language']")
    protected WebElement languageButton;

    @FindBy(xpath = "//ul[@id='dropdown-animated']")
    protected List<WebElement> languageItems;

    public MasterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void set(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void click(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public String getTextFromElement(WebElement element) {
        return element.getText();
    }

    public boolean isWebElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean isWebshopLogoDisplayed() {
        return isWebElementDisplayed(webshopLogo);
    }

    public boolean isWebshopBannerDisplayed() {
        return isWebElementDisplayed(webshopBanner);
    }

    public void selectMenuItemByText(List<WebElement> menuItem, String menuName) {
        for (WebElement item : menuItem) {
            if (getTextFromElement(item).equals(menuName)) {
                click(item);
                //LoggerUtility.infoLog("User navigated on menu " + menuName);
                break;
            }
        }
    }

    public void goToMenuItem(String menuName) {
        selectMenuItemByText(menuItems, menuName);
    }

    public List<String> getMenuItemsText() {
        return menuItems.stream()
                .filter(WebElement::isDisplayed)
                .map(el -> el.getText().replace('\u00A0', ' ').trim())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    public boolean isLanguageButtonDisplayed() {
        return isWebElementDisplayed(languageButton);
    }

    public String getTextFromLanguageButton() {
        return getTextFromElement(languageButton);
    }

}

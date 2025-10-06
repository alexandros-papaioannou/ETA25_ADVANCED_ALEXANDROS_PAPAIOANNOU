package ui.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends MasterPage {

//    @FindBy(xpath = "//div[@class='container']//a[@class='card']")
//    private List<WebElement> productsList;

    private static final By PRODUCT_NAME = By.cssSelector(".card-title");

    @FindBy(css = ".card-title")
    private List<WebElement> productsList;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void selectProduct(String productName) {
        for (WebElement product : productsList) {
            if (getTextFromElement(product).equals(productName)) {
                click(product);
                break;
            }
        }
    }

    private List<WebElement> visibleNameElements() {
        return wait.until(ExpectedConditions
                .refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_NAME)));
    }

    public List<String> getProductsName() {
        WebDriverWait wdWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wdWait.until(d -> {
            try {
                // 1) Wait for at least one VISIBLE .card-title
                List<WebElement> visible =
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_NAME).apply(d);

                if (visible == null || visible.isEmpty()) {
                    return null; // keep waiting
                }

                // 2) Read texts immediately
                List<String> names = visible.stream()
                        .map(WebElement::getText)
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());

                // 3) If texts are still empty (render not finished), retry
                return names.isEmpty() ? null : names;
            } catch (StaleElementReferenceException e) {
                // DOM updated mid-read → retry the whole block
                return null;
            }
        });
    }
}
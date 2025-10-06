package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pom.HomePage;
import ui.support.utils.Hooks;
import ui.support.utils.LoggerUtility;
import ui.support.utils.extent.ExtentUtility;
import ui.support.utils.extent.ReportStep;

import java.util.Arrays;
import java.util.List;

public class CheckProductsTest extends BaseTest {

    @Test
    public void checkProducts() {
        HomePage homePage = new HomePage(driver);

        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Webshop is successfully opened.");


        List<String> expectedProductItems = Arrays.asList("Combination Pliers", "Pliers", "Bolt Cutters", "Long Nose Pliers", "Slip Joint Pliers", "Claw Hammer with Shock Reduction Grip", "Hammer", "Claw Hammer", "Thor Hammer");
        List<String> actualProductItems = homePage.getProductsName();

        LoggerUtility.infoLog("Expected size=" + expectedProductItems.size() + " -> " + expectedProductItems);
        LoggerUtility.infoLog("Actual   size=" + actualProductItems.size()   + " -> " + actualProductItems);

        Assert.assertEquals(expectedProductItems, actualProductItems, "Product items do not match!");
    }
}

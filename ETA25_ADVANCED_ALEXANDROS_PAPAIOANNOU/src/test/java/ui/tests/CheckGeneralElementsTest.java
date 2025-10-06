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

public class CheckGeneralElementsTest extends Hooks {

    @Test
    public void checkGeneralElementsTest() {
        HomePage homePage = new HomePage(driver);

        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Webshop is successfully opened.");

        Assert.assertTrue(homePage.isWebshopLogoDisplayed());
        LoggerUtility.infoLog("Webshop Logo is displayed.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Webshop Logo is successfully displayed.");

        Assert.assertTrue(homePage.isWebshopBannerDisplayed());
        LoggerUtility.infoLog("Webshop Banner is displayed.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Webshop Banner is successfully displayed.");


        List<String> expectedMenuItems = Arrays.asList("Home", "Categories", "Contact", "Sign in");
        List<String> actualMenuItems = homePage.getMenuItemsText();

        LoggerUtility.infoLog("Expected size=" + expectedMenuItems.size() + " -> " + expectedMenuItems);
        LoggerUtility.infoLog("Actual   size=" + actualMenuItems.size()   + " -> " + actualMenuItems);

        Assert.assertEquals(actualMenuItems, expectedMenuItems, "Menu items do not match!");
        LoggerUtility.infoLog("Webshop menu items are displayed.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP,"Webshop menu items: Home, Categories, Contact, Sign in are successfully displayed.");
        ExtentUtility.attachLog(ReportStep.ERROR_STEP,"Webshop menu items missing or incorrectly displayed.");


        Assert.assertTrue(homePage.isLanguageButtonDisplayed());
        LoggerUtility.infoLog("Language button displayed.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Language button is displayed.");
        ExtentUtility.attachLog(ReportStep.ERROR_STEP,"Language button is missing.");

        Assert.assertEquals(homePage.getTextFromLanguageButton(),"EN");
        LoggerUtility.infoLog("Language button is correctly labeled.");
        ExtentUtility.attachLog(ReportStep.PASS_STEP, "Language button is correctly labeled.");
        ExtentUtility.attachLog(ReportStep.ERROR_STEP,"Language button is incorrectly labeled.");
    }
}

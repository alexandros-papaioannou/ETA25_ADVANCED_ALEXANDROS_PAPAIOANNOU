package ui.support.utils;

import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ui.support.utils.extent.ExtentUtility;
import ui.tests.BaseTest;

public class Hooks extends BaseTest {

//    public String testName;
//
//    @BeforeSuite
//    public void initialiseTestReport() {
//        ExtentUtility.initialiseReport();
//    }
//
//    @BeforeMethod(alwaysRun = true)
//    public void prepareEnvironment() {
//        testName = this.getClass().getSimpleName();
//        ThreadContext.put("threadName", testName);
//        LoggerUtility.startTestCase(testName);
//        ExtentUtility.createTest(testName);
//        setUp();
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void clearEnvironment(ITestResult result) {
//        try {
//            if (result.getStatus() == ITestResult.FAILURE) {
//                String errorMessage = result.getThrowable().getMessage();
//                LoggerUtility.errorLog(errorMessage);
//                ExtentUtility.attachLog(testName, errorMessage, driver);
//            }
//            tearDown();
//            LoggerUtility.endTestCase(testName);
//            ExtentUtility.finishTest(testName);
//        } finally {
//            ThreadContext.clearMap();
//        }
//    }
//
//    @AfterSuite
//    public void generateConsolidatedLogs() {
//        LoggerUtility.mergeLogs();
//        ExtentUtility.generateReport();
//    }
}

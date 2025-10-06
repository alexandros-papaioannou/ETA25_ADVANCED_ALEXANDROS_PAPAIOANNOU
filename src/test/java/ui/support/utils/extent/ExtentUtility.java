package ui.support.utils.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

public class ExtentUtility {

    private static ExtentReports extentReports;
    private static final String pathToProject = System.getProperty("user.dir") + "/target/extentReports/";
    private static final ConcurrentHashMap<String, ExtentTest> context = new ConcurrentHashMap<>();

    public static synchronized void initialiseReport() {
        createDirectory();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(pathToProject + "ExtentReport.html");
        htmlReporter.config().setTheme(Theme.DARK);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    public static synchronized void createTest(String testName) {
        ExtentTest testReport = extentReports.createTest(testName + " - report");
        String key = ThreadContext.get("threadName");
        if (key == null) {
            key = Thread.currentThread().getName();
        }
        context.put(key, testReport);
        attachLog(ReportStep.INFO_STEP, "==== START TEST ==== " + testName);
    }

    public static synchronized void finishTest(String testName) {
        attachLog(ReportStep.INFO_STEP, "==== FINISH TEST ==== " + testName);
    }

    private static ExtentTest getCurrentTest() {
        String key = ThreadContext.get("threadName");
        if (key == null) {
            key = Thread.currentThread().getName();
        }
        ExtentTest t = context.get(key);
        if (t == null) {
            t = extentReports.createTest("Untracked thread: " + key);
            context.put(key, t);
        }
        return t;
    }

    public static synchronized void attachLog(String attachType, String message) {
        ExtentTest test = getCurrentTest();
        if (ReportStep.INFO_STEP.equals(attachType)) test.log(Status.INFO, message);
        if (ReportStep.PASS_STEP.equals(attachType)) test.log(Status.PASS, message);
    }

    public static synchronized void attachLog(String reportName, String message, WebDriver driver) {
        ExtentTest test = getCurrentTest();
        test.fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(
                getScreenshot(driver, reportName)).build());
    }

    private static String getScreenshot(WebDriver driver, String reportName) {
        try {
            String path = pathToProject + reportName + ".png";
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(path));
            byte[] imageBytes = IOUtils.toByteArray(Files.newInputStream(Paths.get(path)));
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static synchronized void generateReport() {
        extentReports.flush();
    }

    private static void createDirectory() {
        File directory = new File(pathToProject);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}

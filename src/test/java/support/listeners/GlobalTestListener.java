package support.listeners;

import org.apache.logging.log4j.ThreadContext;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ui.support.utils.LoggerUtility;
import ui.support.utils.extent.ExtentUtility;

public class GlobalTestListener implements ISuiteListener, ITestListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentUtility.initialiseReport();
    }

    @Override
    public void onFinish(ISuite suite) {
        LoggerUtility.mergeLogs();
        ExtentUtility.generateReport();
    }

    @Override
    public void onTestStart(ITestResult r) {
        String name = r.getTestClass().getRealClass().getSimpleName() + " # " + r.getMethod().getMethodName();
        ThreadContext.put("threadName", name);
        LoggerUtility.startTestCase(name);
        ExtentUtility.createTest(name);
    }

    @Override
    public void onTestSuccess(ITestResult r) {
        finish(r, null);
    }

    @Override
    public void onTestSkipped(ITestResult r) {
        finish(r, r.getThrowable());
    }

    @Override
    public void onTestFailure(ITestResult r) {
        String msg = (r.getThrowable() != null && r.getThrowable().getMessage() != null)
                ? r.getThrowable().getMessage() : "Test failed";
        LoggerUtility.errorLog(msg);
        ExtentUtility.attachLog(testName(r), msg); // text-only; add driver overload if you want screenshots
        finish(r, r.getThrowable());
    }

    private void finish(ITestResult r, Throwable t) {
        String name = testName(r);
        LoggerUtility.endTestCase(name);
        ExtentUtility.finishTest(name);
        ThreadContext.clearMap();
    }

    private String testName(ITestResult r) {
        return r.getTestClass().getRealClass().getSimpleName() + " # " + r.getMethod().getMethodName();
    }
}


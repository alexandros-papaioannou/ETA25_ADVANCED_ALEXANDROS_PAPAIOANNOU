package support.logging;

import ui.support.utils.extent.ExtentUtility;
import ui.support.utils.extent.ReportStep;

public final class TestLog {

    private TestLog() {

    }

    public static void info(String message) {
        LoggerUtility.infoLog(message);
        ExtentUtility.attachLog(ReportStep.INFO_STEP, message);
    }

    public static void pass(String message) {
        LoggerUtility.infoLog(message);
        ExtentUtility.attachLog(ReportStep.PASS_STEP, message);
    }

    public static void error(String message) {
        LoggerUtility.errorLog(message);
        ExtentUtility.attachLog(ReportStep.ERROR_STEP, message);
    }

}

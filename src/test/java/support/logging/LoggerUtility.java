package support.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class LoggerUtility {

    private static final String allLogsPath = "target/logs/suite/";
    private static final String consolidatedLogsPath = "target/logs/";

    private static final Logger logger = LogManager.getLogger();

    public static synchronized void startTestCase(String testName) {
        ThreadContext.put("threadName", testName);
        logger.info("############## Execution started: " + testName + " ##############");
    }

    public static synchronized void endTestCase(String testName) {
        logger.info("############## Execution finished: " + testName + " ##############");
        ThreadContext.remove("threadName");
    }

    public static synchronized void infoLog(String message) {
        logger.info(formatMessage(message));
    }

    public static synchronized void errorLog(String message) {
        logger.error(formatMessage(message));
    }

    private static String formatMessage(String message) {
        return " ====> " + message;
    }

    public static void mergeLogs() {
        File dir = new File(allLogsPath);
        String[] fileNames = dir.list();
        if (fileNames == null) return;

        File out = new File(consolidatedLogsPath, "ConsolidatedLogs.log");
        try (PrintWriter pw = new PrintWriter(out)) {
            for (String fileName : fileNames) {
                File f = new File(dir, fileName);
                if (!f.isFile()) continue;
                System.out.println("Reading from " + f.getName());
                pw.println("Contents of file " + f.getName());
                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        pw.println(line);
                    }
                }
                pw.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

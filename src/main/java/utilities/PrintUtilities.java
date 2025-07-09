package utilities;

import configuration.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class PrintUtilities {
    private static final String TEST_OUTPUT_PATH = "target/extent-reports/";
    private static final String SCREENSHOT_FOLDER = "capture/";

    public static void printAssertLog(String actual, String expected) {
        System.out.println("Actual Result   : " + actual);
        System.out.println("Expected Result : " + expected);
    }

    public static String screenCapture(String methodName) throws IOException {
        File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

        String fileName = methodName + "-" + GeneralUtilities.generateTimeNow(1) + ".png";
        String fileNameWithDestinationPath = TEST_OUTPUT_PATH + SCREENSHOT_FOLDER + fileName;
        String relativePath = SCREENSHOT_FOLDER + fileName;

        FileUtils.copyFile(scrFile, new File(fileNameWithDestinationPath));

        return relativePath;
    }

    public static String reportLocation() {
        return TEST_OUTPUT_PATH + "ExtentReport-" + GeneralUtilities.generateTimeNow(1) + ".html";
    }
}

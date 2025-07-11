package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import configuration.DriverManager;
import constans.DefaultSettings;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import pom.DashboardPage;
import pom.LoginPage;
import pom.NavigationPane;
import utilities.PrintUtilities;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
    protected static ExtentReports extent = new ExtentReports();
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static JavascriptExecutor executor;
    protected static Actions actions;

    protected static ExtentSparkReporter spark;
    protected static ExtentTest test;
    protected static ExtentTest node;

    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected NavigationPane navigationPane;

    @BeforeTest
    public void setup() {
        System.out.println("+++++++++++++++++++++++");
        System.out.println("Before Test");
        System.out.println("+++++++++++++++++++++++");

        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, 20);
        executor = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        spark = new ExtentSparkReporter(PrintUtilities.reportLocation());
    }

    @AfterTest
    public void teardown() {
        System.out.println("+++++++++++++++++++++++");
        System.out.println("After Test");
        System.out.println("+++++++++++++++++++++++");

        driver.close();
        extent.attachReporter(spark);
        extent.flush();
    }

    @BeforeClass
    public void startApp() {
        System.out.println("=======================");
        System.out.println("Before Class");
        System.out.println("=======================");

        ITestResult itr = Reporter.getCurrentTestResult();

        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        navigationPane = new NavigationPane();

        driver.get(DefaultSettings.WEB_URL);
        test = extent.createTest(itr.getInstance().getClass().getSimpleName());
    }

    @AfterClass
    public void closeApp() {
        System.out.println("=======================");
        System.out.println("After Class");
        System.out.println("=======================");
    }

    @BeforeMethod
    public void startTestStep(Method method) {
        String methodName = method.getName();
        System.out.println("-----------------------");
        System.out.println("Test " + methodName + " Start");
        System.out.println("-----------------------");
        node = test.createNode(methodName);
    }

    @AfterMethod
    public void finishTestStep(ITestResult result) throws IOException {
        String methodName = result.getMethod().getMethodName();
        String pathCapture = PrintUtilities.screenCapture(methodName);
        node.info(result.getMethod().getDescription());

        System.out.println("-----------------------");

        if (result.getStatus() == 1) {
            System.out.println((methodName + " passed"));
            node.addScreenCaptureFromPath(pathCapture);
        } else if (result.getStatus() == 2) {
            System.out.println((methodName + " failed"));
            node.fail(result.getThrowable());
            node.addScreenCaptureFromPath(pathCapture);
        } else if (result.getStatus() == 3) {
            System.out.println((methodName + " skipped"));
            node.skip(result.getThrowable());
            node.addScreenCaptureFromPath(pathCapture);
        } else {
            System.out.println((methodName + " passed"));
            node.addScreenCaptureFromPath(pathCapture);
        }

        System.out.println("-----------------------");
    }
}

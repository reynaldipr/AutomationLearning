package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverManager<T> {
    private static ThreadLocal<DriverManager> instance = new ThreadLocal<>();
    private WebDriver driver;

    private DriverManager() {
        createDriver(ConfigurationManager.DRIVER_TYPE == null ? "Chrome" : ConfigurationManager.DRIVER_TYPE);
    }

    private static synchronized DriverManager getInstance() {
        if (instance.get() == null) {
            instance.set(new DriverManager());
        }
        return instance.get();
    }

    private void createDriver(String driverType) {
        String osName = System.getProperty("os.name");
        String path = System.getProperty("user.dir");
        ChromeOptions chromeOptions = new ChromeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        switch (driverType) {
            case "Chrome":
                chromeOptions.addArguments("start-maximized");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
            case "Firefox":
                firefoxOptions.addArguments("start-maximized");
                firefoxOptions.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
                this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
            case "ChromeHeadless":
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--headless");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
            case "RemoteChrome":
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),chromeOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                break;
            default:
                throw new UnsupportedOperationException(
                        "unknown driver type !!! please, check your command line parameter: -Ddriver.type");
        }
    }

    public static <T> T getDriver() {
        return (T) getInstance().driver;
    }
}

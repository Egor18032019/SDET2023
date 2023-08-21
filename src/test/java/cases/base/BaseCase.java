package cases.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.Const;
import utils.Utils;

import java.time.Duration;


public class BaseCase {
    public static WebDriverWait wait;
    public static WebDriver driver;

    @BeforeSuite
    public void openURL() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        if (Utils.isWindows()) {
            System.setProperty("webdriver.chrome.driver", Const.pathGoogleDriver);
        }
        driver.manage().window().maximize();
        driver.navigate().to(Const.urlMain);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterSuite()
    public void closeBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}

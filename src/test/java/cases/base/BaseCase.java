package cases.base;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.Const;
import utils.Utils;

import java.time.Duration;


public class BaseCase {
    public static WebDriverWait wait;
    public WebDriver driver;

    @BeforeSuite
    public void openURL() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        if (Utils.isWindows()) {
            System.setProperty("webdriver.chrome.driver", Const.pathGoogleDriver);
        }
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");


        driver = new ChromeDriver(options);

//        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
//        driver.manage().window().setSize(new Dimension(400, 1200));

        driver.navigate().to(Const.urlMain);

        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    }

    @AfterSuite()
    public void closeBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}

package tests.base;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.Const;
import utils.Utils;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;

import static utils.Utils.takeScreenShot;

/**
 * Родительский класс в котором прописано открытие браузера и закрытие браузера
 * + инициализация WebDriver и WebDriverWait
 */
public class BaseCase {
    public WebDriver driver;
    public WebDriverWait webDriverWait;
    public static final Logger LOGGER = LoggerFactory.getLogger(BaseCase.class);

    static {
        LOGGER.info("Test start time:" + LocalTime.now());
    }

    @BeforeSuite
    @Step("Открытие браузера и переход на страницу")
    public void openURL() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        if (Utils.isWindows()) {
            System.setProperty("webdriver.chrome.driver", Const.pathGoogleDriver);
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(1));
        driver.navigate().to(Const.urlMain);
    }

    @AfterSuite()
    @Step("Чистка кэша и закрытие браузера.")
    public void closeBrowser() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.sessionStorage.clear()");
        driver.manage().deleteAllCookies();
        driver.quit();
        LOGGER.info("Test end time:" + LocalTime.now());
    }
    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            takeScreenShot(driver);
        }
    }

}
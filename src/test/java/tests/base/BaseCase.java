package tests.base;

import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.Const;
import utils.Utils;

import java.time.Duration;

/**
 * Родительский класс в котором прописано открытие браузера и закрытие браузера
 * + инициализация WebDriver и WebDriverWait
 */
public class BaseCase {
    public WebDriver driver;
    public static WebDriverWait wait;

    @BeforeSuite
    @Step("Открытие браузера и переход на страницу")
    public void openURL() {
        if (Utils.isWindows()) {
            System.setProperty("webdriver.http.factory", "jdk-http-client");
            System.setProperty("webdriver.chrome.driver", Const.pathGoogleDriver);
        } else {
            System.out.println("eto linux");
            System.setProperty("webdriver.chrome.driver", Const.pathGoogleDriverLinux);

        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
//        driver.manage().window().fullscreen();
//        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1280, 1024));
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));

        driver.navigate().to(Const.urlMain);
    }

    @AfterSuite()
    @Step("Открытие браузера и переход на страницу")
    public void closeBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}

package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.Map;

public class Utils {
    static Map<String, String> monthsAsMap = Map.ofEntries(
            Map.entry("January", "0"),
            Map.entry("February", "1"),
            Map.entry("March", "2"),
            Map.entry("April", "3"),
            Map.entry("May", "4"),
            Map.entry("June", "5"),
            Map.entry("July", "6"),
            Map.entry("August", "7"),
            Map.entry("September", "8"),
            Map.entry("October", "9"),
            Map.entry("November", "10"),
            Map.entry("December", "11")
    );


    public static WebDriver getWebDriver(Browser en) {

        switch (en) {
            case FIREFOX:
                return new FirefoxDriver();
            case CHROME:
            default:
                return new ChromeDriver();
        }
    }

    public enum Browser {
        CHROME, FIREFOX
    }

    public static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("win"));

    }

    public static String giveMeNumberMonth(String month) {
        return monthsAsMap.get(month);

    }
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public static byte[] takeScreenShot(WebDriver browser) {
        return ((TakesScreenshot) browser).getScreenshotAs(OutputType.BYTES);
    }
    @Attachment(value = "Values")
    public static byte[] takeValues(WebDriver browser) {
        return Const.storage.toString().getBytes();
    }
}


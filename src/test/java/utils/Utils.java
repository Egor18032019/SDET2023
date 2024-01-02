package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Utils {

    public static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("win"));

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


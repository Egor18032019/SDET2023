package pages.base;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageBase {

    protected WebDriver driver;

    public PageBase(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    protected static void clickButton(WebElement button) {
        button.click();
    }

    protected static void setTextElementText(WebElement textElement, String value) {
        textElement.sendKeys(value);
    }
    protected static void pushEnter(WebElement textElement ) {
        textElement.sendKeys(Keys.ENTER);
    }
}

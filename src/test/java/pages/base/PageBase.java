package pages.base;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Waiters;

public class PageBase {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    public PageBase(WebDriver driver,WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        webDriver = driver;
        webDriverWait = wait;
    }

    protected static void clickOnElement(WebElement button) {
        button.click();
    }
    @Step("Ввод значения '{value}'")
    protected static void setTextElementText(WebElement textElement, String value) {
        textElement.clear();
        textElement.sendKeys(value);
    }
    @Step("Нажали Enter")
    protected static void pushEnter(WebElement textElement) {
        textElement.sendKeys(Keys.ENTER);
    }
    @Step("Ввод значения '{value}'")
    public static void inputWithEnter(WebDriverWait wait,WebElement element, String value) {
        Waiters.waitVisibilityElement(element, wait);
        element.clear();
        element.sendKeys(value);
        Waiters.waitVisibilityElement(element, wait);
        pushEnter(element);
    }
    @Step("Из выпадающего списка выбрать '{value}'")
    public static void select(WebDriverWait wait,WebElement element, String value) {
        Waiters.waitVisibilityElement(element, wait);
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }
    public static  void scrollWithJavaScript(WebElement element,WebDriver webDriver) {
        String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
        ((JavascriptExecutor) webDriver).executeScript(mouseOverScript, element);
    }
}


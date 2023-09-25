package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * методы для явного ожидания
 */
public class Waiters {
  @Step("Ожидание появления элемента")
    public static void waitVisibilityElement(WebElement element, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

   @Step("Ожидание модального окна")
    public static void WaitingModalWindow(WebDriverWait wait) {
        wait.until(ExpectedConditions.alertIsPresent());
    }
}

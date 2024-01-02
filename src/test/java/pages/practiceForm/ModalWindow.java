package pages.practiceForm;

import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.base.PageBase;
import utils.Utils;
import utils.Waiters;

import java.util.Map;

public class ModalWindow extends PageBase {
    public ModalWindow(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement modalTitle;

    @FindBy(xpath = "//table/tbody")
    private WebElement modalTableBody;


    @Step("Проверка заголовка модального окна")
    public ModalWindow checkingTitle(String answer) {
        Waiters.waitVisibilityElement(modalTitle, this.webDriverWait);
        Utils.takeScreenShot(webDriver);
        String text = modalTitle.getText();
        boolean isGood = text.equals(answer);
        Assert.assertTrue(isGood);
        return this;
    }

    @Step("Сравнение значений")
    @Story("В таблице на окне отображаются введенные ранее значения")
    public void comparisonValues(Map<String, String> storage) {
        Utils.takeScreenShot(webDriver);
        Utils.takeValues(webDriver);
        for (Map.Entry<String, String> entry : storage.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            checkTableRow(modalTableBody, key, value);
        }
    }

    @Step("Строка таблицы: '{label}', Ожидаемый результат: '{value}'")
    public void checkTableRow(WebElement element, String label, String value) {
        Waiters.waitVisibilityElement(element, this.webDriverWait);
        WebElement rowKey = (element.findElement(By.xpath(String.format("./tr/td[contains(text(),'%s')]", label))));
        String rowValue = rowKey.findElement(By.xpath("./following::td")).getText();
        Assert.assertEquals(value, rowValue);
    }
}

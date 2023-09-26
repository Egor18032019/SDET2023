package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.testng.Assert;
import pages.base.PageBase;
import utils.Const;
import utils.Utils;
import utils.Waiters;

import java.util.List;
import java.util.Map;

import static tests.base.BaseCase.wait;
import static utils.Utils.giveMeNumberMonth;

/**
 * Practice Form page
 */
public class PracticeForm extends PageBase {
    public PracticeForm(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Первое поле в строке Name
     */
    @FindBy(id = "firstName")
    public WebElement firstName;
    /**
     * Второе поле в строке Name
     */
    @FindBy(id = "lastName")
    public WebElement lastName;
    /**
     * Поле Email
     */
    @FindBy(xpath = "//input[@id='userEmail']")
    public WebElement userEmail;
    /**
     * Список radio для выбора пола(Gender)
     */
    @FindBys({@FindBy(xpath = "//label[@class='custom-control-label']")})
    public List<WebElement> allGenders;

    /**
     * Поле для вода номера телефона
     */
    @FindBy(xpath = "//input[@id='userNumber']")
    public WebElement mobileNumber;
    /**
     * Поле для вода день рождения(вызывает datepicker)
     */
    @FindBy(xpath = "//div[@class='react-datepicker__input-container']")
    public WebElement dateOfBirthInput;
    /**
     * Кнопка выбор месяца в datepickere
     */
    @FindBy(xpath = "//select[@class='react-datepicker__month-select']")
    public WebElement monthSelect;
    /**
     * Кнопка выбора года в datepickere
     */
    @FindBy(xpath = "//select[@class='react-datepicker__year-select']")
    public WebElement yearSelect;
    /**
     * Поле Subjects
     */
    @FindBy(xpath = "//input[@id='subjectsInput']")
    public WebElement subjectsInput;
    /**
     * Поле со списком дней
     */
    @FindBy(xpath = "//div[@class='react-datepicker__month']")
    public WebElement dayContainer;
    /**
     * Поле для текстового вода адреса
     */
    @FindBy(xpath = "//textarea[@placeholder='Current Address']")
    public WebElement currentAddress;
    /**
     * Select State
     */
    @FindBy(xpath = "//div[@id='state']")
    public WebElement stateButton;
    @FindBy(xpath = "//div[@id='state'] //input")
    public WebElement stateInput;
    @FindBy(xpath = "//div[@id='city'] //input")
    public WebElement cityInput;
    /**
     * Select city button
     */
    @FindBy(xpath = "//div[@id='city']")
    public WebElement cityButton;

    /**
     * меню для выбора state или city
     */
    @FindBy(xpath = "//div[@class=' css-26l3qy-menu']")
    public WebElement menu;
    /**
     * Загрузка файла input
     */
    @FindBy(xpath = "//input[@id='uploadPicture']")
    public WebElement uploadPicture;
    /**
     * Итоговое модальное окно
     */
    @FindBy(xpath = "//div[@class='modal-header']")
    public WebElement modal;
    /**
     * Кнопка отправки формы
     */
    @FindBy(xpath = "//div//button[@id='submit']")
    public WebElement submit;

    @Step(" 1. Заполнить поле First Name значением {first}")
    public PracticeForm fillFirstName(String first) {
        PageBase.setTextElementText(firstName, first);
        return this;
    }

    @Step("2. Заполнить поле Last Name значением {last}")
    public PracticeForm fillLastName(String last) {
        PageBase.setTextElementText(lastName, last);
        return this;
    }

    @Step("3. Заполнить поле Email строкой значением {mail}")
    public PracticeForm fillMail(String mail) {
        PageBase.setTextElementText(userEmail, mail);
        return this;
    }

    @Step("4. Выбрать значение в Gender = {gender} с помощью переключателя")
    public PracticeForm chooseGender(String gender) {
        for (WebElement webElement : allGenders) {
            if (webElement.getText().equalsIgnoreCase(gender)) {
                webElement.click();
                break;
            }
        }
        return this;
    }

    @Step("5. Заполнить поле Mobile значением {mobile} ")
    public PracticeForm fillMobile(String mobile) {
        scrollWithJavaScript(mobileNumber);
        PageBase.setTextElementText(mobileNumber, mobile);
        return this;
    }

    @Step(" 6. Заполнить поле Date of birth с помощью всплывающего календаря")
    public PracticeForm fillDate(String year, String month, String dateTime) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", dateOfBirthInput);
        PageBase.clickButton(dateOfBirthInput);
        Waiters.waitVisibilityElement(dayContainer, wait);
        //выбор месяца
        // Первый вариант
        String numberMonth = giveMeNumberMonth(month);
        String valueMonth = "option[@value=" + numberMonth + "]";
        PracticeForm.clickButton(monthSelect.findElement(By.xpath(valueMonth)));
        //или через цикл
        //   List<WebElement> allMontns = monthContainer.findElements(By.xpath(
        String valueYear = "option[@value=" + year + "]";
        PracticeForm.clickButton(yearSelect.findElement(By.xpath(valueYear)));
        // выбор дня
        List<WebElement> allTime = dayContainer.findElements(By.xpath("//div[@class='react-datepicker__week']//div[contains(@class,'react-datepicker__day')]"));
        for (WebElement webElement : allTime) {
            if (webElement.getText().equalsIgnoreCase(dateTime)) {
                // + доп проверка на месяц
                String label = webElement.getAttribute("aria-label");
                if (label.contains(month)) {
                    webElement.click();
                    break;
                }
            }
        }
        return this;
    }

    @Step("7. Заполнить поле Subjects значением {subjects}")
    public PracticeForm fillSubjects(String subjects) {
        scrollWithJavaScript(subjectsInput);
        String[] subjectsForArray = subjects.split(",");
        Waiters.waitVisibilityElement(subjectsInput, wait);
        for (String subject : subjectsForArray) {
            PracticeForm.setTextElementText(subjectsInput, subject);
            pushEnter(subjectsInput);
        }
        return this;
    }

    @Step("8. Загрузить любое изображение в поле Picture")
    public PracticeForm uploadPicture(String filePath) {
        scrollWithJavaScript(uploadPicture);
        Waiters.waitVisibilityElement(uploadPicture, wait);
        PracticeForm.setTextElementText(uploadPicture, filePath);
        return this;
    }

    @Step("9. Заполнить поле Current Address значением {address}")
    public PracticeForm fillAddress(String address) {
        scrollWithJavaScript(currentAddress);
        Waiters.waitVisibilityElement(currentAddress, wait);
        PracticeForm.setTextElementText(currentAddress, address);
        return this;
    }

    @Step("10. Выбрать значение = {state} в Select State с помощью выпадающего списка")
    public PracticeForm fillState(String state) {
        scrollWithJavaScript(stateButton);
        PracticeForm.clickButton(stateButton);
        Waiters.waitVisibilityElement(menu, wait);
        stateInput.sendKeys(state);
        pushEnter(stateInput);
//        List<WebElement> listState = menu.findElements(By.xpath("//div[contains(@id,'react-select-3-option-')]"));
//        for (WebElement webElement : listState) {
//            if (webElement.getText().equalsIgnoreCase(state)) {
//                scrollWithJavaScript(webElement);
//                webElement.click();
//                break;
//            }
//        }
        return this;
    }

    @Step("11. Выбрать любое значение = {city} в Select City с помощью выпадающего списка")
    public PracticeForm fillCity(String city) {
        scrollWithJavaScript(cityButton);
        PracticeForm.clickButton(cityButton);
        Waiters.waitVisibilityElement(menu, wait);
        cityInput.sendKeys(city);
        pushEnter(cityInput);
//        List<WebElement> listCity = menu.findElements(By.xpath("//div[contains(@id,'react-select-4-option-')]"));
//        for (WebElement webElement : listCity) {
//            if (webElement.getText().equalsIgnoreCase(city)) {
//                scrollWithJavaScript(webElement);
//                webElement.click();
//                break;
//            }
//        }
        return this;
    }

    @Step("12. Нажать кнопку Submit")
    public void submitForm() {
        Utils.takeScreenShot(webDriver);
        scrollWithJavaScript(submit);
        PracticeForm.clickButton(submit);
    }

    @Step("Проверка модального окна")
    public void modalWindow() {
        Waiters.waitVisibilityElement(modal, wait);
        String text = modal.getText();
        String answer = "Thanks for submitting the form";
        boolean isGood = text.equals(answer);
        Assert.assertTrue(isGood);
    }

    @Step("Сравнение значений")
    @Story("В таблице на окне отображаются введенные ранее значения")
    public void comparisonValues(Map<String, String> storage) {
        Utils.takeScreenShot(webDriver);
        Utils.takeValues(webDriver);
        Allure.addAttachment("Val", Const.storage.toString());
        List<WebElement> listCell = webDriver.findElements(By.xpath("//table[contains(@class,'table ')]//td")); // 20
        String standard = "";
        for (int i = 0; i < listCell.size(); i++) {
            if (i % 2 == 0) {
                String key = listCell.get(i).getText();
                standard = storage.get(key);
            } else {
                String value = listCell.get(i).getText();
                Assert.assertEquals(value, standard);
            }
        }
    }

    private void scrollWithJavaScript(WebElement element) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
    }
}

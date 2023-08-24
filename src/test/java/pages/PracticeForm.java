package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import pages.base.PageBase;
import utils.Waiters;

import java.util.List;

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
    WebElement firstName;
    /**
     * Второе поле в строке Name
     */
    @FindBy(id = "lastName")
    WebElement lastName;
    /**
     * Поле Email
     */
    @FindBy(xpath = "//input[@id='userEmail']")
    WebElement userEmail;
    /**
     * Список radio для выбора пола(Gender)
     */
    @FindBys({@FindBy(xpath = "//label[@class='custom-control-label']")})
    List<WebElement> allGenders;

    /**
     * Поле для вода номера телефона
     */
    @FindBy(xpath = "//input[@id='userNumber']")
    WebElement mobileNumber;
    /**
     * Поле для вода день рождения(вызывает datepicker)
     */
    @FindBy(xpath = "//div[@class='react-datepicker__input-container']")
    WebElement dateOfBirthInput;
    /**
     * Кнопка выбор месяца в datepickere
     */
    @FindBy(xpath = "//select[@class='react-datepicker__month-select']")
    WebElement monthSelect;
    /**
     * Кнопка выбора года в datepickere
     */
    @FindBy(xpath = "//select[@class='react-datepicker__year-select']")
    WebElement yearSelect;
    /**
     * Поле Subjects
     */
    @FindBy(xpath = "//input[@id='subjectsInput']")
    WebElement subjectsInput;
    /**
     * поле со списком дней
     */
    @FindBy(xpath = "//div[@class='react-datepicker__month']")
    WebElement dayContainer;
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
    WebElement uploadPicture;
    /**
     * Итоговое модальное окно
     */
    public @FindBy(xpath = "//div[@class='modal-header']")
    WebElement modal;
    /**
     * Кнопка отправки формы
     */
    public @FindBy(xpath = "//div//button[@id='submit']")
    WebElement submit;

    @Step("Заполнение формы")
    public void fillForm(String first, String last, String mail, String gender, String mobile, String filePath, String dateTime, String month, String year, String subjects,
                         String address, String state, String city) {
//        1. Заполнить поле First Name произвольной строкой
        PageBase.setTextElementText(firstName, first);
//        2. Заполнить поле Last Name произвольной строкой
        PageBase.setTextElementText(lastName, last);
//        3. Заполнить поле Email строкой формата name@example.com
        PageBase.setTextElementText(userEmail, mail);
//        Выбрать значение в Gender с помощью переключателя
        for (WebElement webElement : allGenders) {
            if (webElement.getText().equalsIgnoreCase(gender)) {
                webElement.click();
                break;
            }
        }
//        5. Заполнить поле Mobile произвольными 10 цифрами
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", mobileNumber);
        PageBase.setTextElementText(mobileNumber, mobile);
//        6. Заполнить поле Date of birth произвольной датой с помощью всплывающего календаря
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", dateOfBirthInput);
        PageBase.clickButton(dateOfBirthInput);
        Waiters.waitVisibilityElement(dayContainer, wait);
        //выбор месяца
        // Можно так
        String numberMonth = giveMeNumberMonth(month);
        String valueMonth = "option[@value=" + numberMonth + "]";
        PracticeForm.clickButton(monthSelect.findElement(By.xpath(valueMonth)));
        //или через цикл
        //   List<WebElement> allMontns = monthContainer.findElements(By.xpath(

        // выбор года
//        PracticeForm.clickButton(yearSelect); //TODO надо ли проверять клик на кнопку и что открылось?
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
//      7. Заполнить поле Subjects произвольной строкой
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", subjectsInput);
        String[] subjectsForArray = subjects.split(",");
        Waiters.waitVisibilityElement(subjectsInput, wait);
        for (String subject : subjectsForArray) {
            PracticeForm.setTextElementText(subjectsInput, subject);
            PracticeForm.pushEnter(subjectsInput);
        }
        //8. Загрузить любое изображение в поле Picture
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", uploadPicture);

        Waiters.waitVisibilityElement(uploadPicture, wait);
        PracticeForm.setTextElementText(uploadPicture, filePath);
        //9. Заполнить поле Current Address произвольной строкой
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", currentAddress);
        Waiters.waitVisibilityElement(currentAddress, wait);
        PracticeForm.setTextElementText(currentAddress, address);
        //10. Выбрать любое значение в Select State с помощью выпадающего списка
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", stateButton);
        PracticeForm.clickButton(stateButton);
        Waiters.waitVisibilityElement(menu, wait);
        List<WebElement> listState = menu.findElements(By.xpath("//div[contains(@id,'react-select-3-option-')]"));
        for (WebElement webElement : listState) {
            if (webElement.getText().equalsIgnoreCase(state)) {
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", webElement);
                break;
            }
        }
//        11. Выбрать любое значение в Select City с помощью выпадающего списка
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", cityButton);
        PracticeForm.clickButton(cityButton);
        Waiters.waitVisibilityElement(menu, wait);
        List<WebElement> listCity = menu.findElements(By.xpath("//div[contains(@id,'react-select-4-option-')]"));
        for (WebElement webElement : listCity) {
            if (webElement.getText().equalsIgnoreCase(city)) {
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", webElement);
//                PracticeForm.clickButton(webElement); //Закрывает другим элементом при некоторых расширениях
                break;
            }
        }
//        12. Нажать кнопку Submit
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", submit);
        PracticeForm.clickButton(submit);
    }
}
/*
   //        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
//        executor.executeScript("document.body.style.zoom = '0.50'");

<div class=" css-11unzgr">
<div class=" css-9gakcf-option" id="react-select-3-option-0" tabindex="-1">NCR</div>
<div class=" css-yt9ioa-option" id="react-select-3-option-1" tabindex="-1">Uttar Pradesh</div>
<div class=" css-yt9ioa-option" id="react-select-3-option-2" tabindex="-1">Haryana</div>
<div class=" css-yt9ioa-option" id="react-select-3-option-3" tabindex="-1">Rajasthan</div></div></div>

<div class=" css-26l3qy-menu">
<div class=" css-11unzgr">
<div class=" css-yt9ioa-option" id="react-select-4-option-0" tabindex="-1">Delhi</div>
<div class=" css-yt9ioa-option" id="react-select-4-option-1" tabindex="-1">Gurgaon</div>
<div class=" css-9gakcf-option" id="react-select-4-option-2" tabindex="-1">Noida</div>
</div>
</div>

   */
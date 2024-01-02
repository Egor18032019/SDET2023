package pages.practiceForm;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.PageBase;
import utils.Utils;
import utils.Waiters;

import java.util.List;

/**
 * Practice Form page
 */
public class Form extends PageBase {
    public Form(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    /**
     * Первое поле в строке Name
     */
    @FindBy(id = "firstName")
    public WebElement firstNameElement;
    /**
     * Второе поле в строке Name
     */
    @FindBy(id = "lastName")
    public WebElement lastNameElement;
    /**
     * Поле Email
     */
    @FindBy(xpath = "//input[@id='userEmail']")
    public WebElement userEmailElement;
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
     * Поле для вода день рождения(вызывает date picker)
     */
    @FindBy(xpath = "//input[@id='dateOfBirthInput']")
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
    @FindBy(xpath = "//*[@id='react-select-3-input']")
    private WebElement stateSelect;
    @FindBy(xpath = "//*[@id='react-select-4-input']")
    private WebElement citySelect;
    /**
     * Загрузка файла input
     */
    @FindBy(xpath = "//input[@id='uploadPicture']")
    public WebElement uploadPicture;

    /**
     * Кнопка отправки формы
     */
    @FindBy(xpath = "//div//button[@id='submit']")
    public WebElement submit;

    @Step(" 1. Заполнить поле First Name значением {firstName}")
    public Form fillFirstName(String firstName) {
       setTextElementText(firstNameElement, firstName);
        return this;
    }

    @Step("2. Заполнить поле Last Name значением {lastName}")
    public Form fillLastName(String lastName) {
        setTextElementText(lastNameElement, lastName);
        return this;
    }

    @Step("3. Заполнить поле Email строкой значением {mail}")
    public Form fillMail(String mail) {
      setTextElementText(userEmailElement, mail);
        return this;
    }

    @Step("4. В поле 'Gender' выбрать значение = {gender}")
    public Form chooseGender(String gender) {
        for (WebElement webElement : allGenders) {
            if (webElement.getText().equalsIgnoreCase(gender)) {
                webElement.click();
                break;
            }
        }
        return this;
    }

    @Step("5. Заполнить поле Mobile значением {mobile} ")
    public Form fillMobile(String mobile) {
        scrollWithJavaScript(mobileNumber, this.webDriver);
        setTextElementText(mobileNumber, mobile);
        return this;
    }

    @Step(" 6. Заполнить поле Date of birth с помощью всплывающего календаря")
    public Form fillDate(String year, String month, String day) {
        Waiters.waitVisibilityElement(dateOfBirthInput, this.webDriverWait);
        scrollWithJavaScript(dateOfBirthInput, this.webDriver);
        clickOnElement(dateOfBirthInput);
        select(this.webDriverWait, yearSelect, year);
        select(this.webDriverWait, monthSelect, month);
        clickOnElement(webDriver.findElement(By.xpath(String.format(".//div[contains(@class, 'react-datepicker__day react-datepicker__day--0%s')]", day))));
        return this;
    }

    @Step("7. Заполнить поле Subjects значением {subjects}")
    public Form fillSubjects(String subjects) {
        scrollWithJavaScript(subjectsInput, this.webDriver);
        String[] subjectsForArray = subjects.split(",");
        Waiters.waitVisibilityElement(subjectsInput, this.webDriverWait);
        for (String subject : subjectsForArray) {
            Form.setTextElementText(subjectsInput, subject);
            pushEnter(subjectsInput);
        }
        return this;
    }

    @Step("8. Загрузить любое изображение в поле Picture")
    public Form uploadPicture(String filePath) {
        scrollWithJavaScript(uploadPicture, this.webDriver);
        Waiters.waitVisibilityElement(uploadPicture, this.webDriverWait);
        Form.setTextElementText(uploadPicture, filePath);
        return this;
    }

    @Step("9. Заполнить поле Current Address значением {address}")
    public Form fillAddress(String address) {
        scrollWithJavaScript(currentAddress, this.webDriver);
        Waiters.waitVisibilityElement(currentAddress, this.webDriverWait);
        Form.setTextElementText(currentAddress, address);
        return this;
    }

    @Step("10. Выбрать значение = {state} в Select State с помощью выпадающего списка")
    public Form fillState(String state) {
        scrollWithJavaScript(stateButton, this.webDriver);
        Form.clickOnElement(stateButton);
        Waiters.waitVisibilityElement(menu, this.webDriverWait);
        inputWithEnter(this.webDriverWait, stateSelect, state);
        return this;
    }

    @Step("11. Выбрать любое значение = {city} в Select City с помощью выпадающего списка")
    public Form fillCity(String city) {
        scrollWithJavaScript(cityButton, this.webDriver);
        Form.clickOnElement(cityButton);
        Waiters.waitVisibilityElement(menu, this.webDriverWait);
        inputWithEnter(this.webDriverWait, citySelect, city);
        return this;
    }

    @Step("12. Нажать кнопку Submit")
    public void submitForm() {
        scrollWithJavaScript(submit, this.webDriver);
        Utils.takeScreenShot(webDriver);
        Waiters.waitVisibilityElement(submit, this.webDriverWait);
        Form.clickOnElement(submit);
    }



}
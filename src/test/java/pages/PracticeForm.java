package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.PageBase;
import utils.Waiters;

import java.util.List;

import static cases.base.BaseCase.wait;

public class PracticeForm extends PageBase {
    public PracticeForm(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='userEmail']")
    WebElement userEmail;

    @FindBy(xpath = "//label[@class='custom-control-label']")
    public WebElement male;
    @FindBy(xpath = "//input[@id='userNumber']")
    WebElement mobileNumber;
    @FindBy(xpath = "//div[@class='react-datepicker__input-container']")
    WebElement dateOfBirthInput;
    @FindBy(xpath = "//select[@class='react-datepicker__month-select']")
    WebElement monthSelect;
    @FindBy(xpath = "//option[@value='2']")
    WebElement march;
    @FindBy(xpath = "//input[@id='subjectsInput']")
    WebElement subjectsInput;
    @FindBy(xpath = "//div[@class='react-datepicker__month']")
    WebElement month;


    /**
     * Поле для текстового вода адреса
     */
    @FindBy(xpath = "//textarea[@placeholder='Current Address']")
    public WebElement currentAddress;
    @FindBy(xpath = "//div[@id='state']")
    WebElement state;


    //    @FindBy(xpath = "//input[@id='react-select-3-input']")
    @FindBy(xpath = "//div[@id='state']")
    public WebElement stateInput;
    //    @FindBy(xpath = "//div[@calss='react-select-4-input']")
    @FindBy(xpath = "//div[@id='city']")
    public WebElement cityInput;
    //    @FindBy(xpath = "//div[@calss='css-2613qy-menu']")
//    public WebElement menu;
    @FindBy(xpath = "//input[@id='uploadPicture']")
    WebElement uploadPicture;

    public @FindBy(xpath = "//div[@id='modal-header']")
    WebElement modal;

    //    public @FindBy(xpath = "//div//button[@id='submit']")
    public @FindBy(xpath = "//a[@id='close-fixedban']")
    WebElement submit;

    public void fillForm(String first, String last, String mail, String mobile, String filePath, String dateTime) throws InterruptedException {
        PageBase.setTextElementText(firstName, first);
        PageBase.setTextElementText(lastName, last);
        PageBase.setTextElementText(userEmail, mail);
        PageBase.clickButton(male);
        PageBase.setTextElementText(mobileNumber, mobile);
        PageBase.clickButton(dateOfBirthInput);
        Waiters.waitVisibilityElement(month, wait);
        PageBase.clickButton(monthSelect);
        Waiters.waitVisibilityElement(month, wait);
        PageBase.clickButton(monthSelect.findElement(By.xpath("option[@value='2']")));
        Waiters.waitVisibilityElement(month, wait);
        System.out.println("male " + male.getText());

        List<WebElement> allTime = month.findElements(By.xpath("//div[@class='react-datepicker__week']//div[contains(@class,'react-datepicker__day')]"));
        //TODO проверить колекцию
        for (WebElement webElement : allTime) {
            if (webElement.getText().equalsIgnoreCase(dateTime)) {
                webElement.click();
                break;
            }
        }
        Waiters.waitVisibilityElement(subjectsInput, wait);
        PracticeForm.setTextElementText(subjectsInput, "English");//
        PracticeForm.pushEnter(subjectsInput);
        PracticeForm.setTextElementText(subjectsInput, "Maths");
        PracticeForm.pushEnter(subjectsInput);
        Thread.sleep(2222);
        Waiters.waitVisibilityElement(currentAddress, wait);
        PracticeForm.setTextElementText(currentAddress, "currentAddress");

        PracticeForm.clickButton(stateInput);
        WebElement menu = stateInput.findElement(By.xpath("//div[@class='menu']"));
        Waiters.waitVisibilityElement(menu, wait);
        PracticeForm.clickButton(menu);

        Waiters.waitVisibilityElement(cityInput, wait);
    menu = cityInput.findElement(By.xpath("//div[@class='menu']"));

        System.out.println(menu.getShadowRoot());
        System.out.println(menu.getTagName());
        PracticeForm.clickButton(cityInput);
//
//        PracticeForm.setTextElementText(cityInput, "Karnal");
//        PracticeForm.clickButton(cityInput);

        Waiters.waitVisibilityElement(uploadPicture, wait);
        System.out.println(allTime.size() + " filePath");
        PracticeForm.setTextElementText(uploadPicture, filePath);
        PracticeForm.clickButton(submit);
        Thread.sleep(2222222);


    }
}
/*

 */
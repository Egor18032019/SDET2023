package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PracticeForm;
import tests.base.BaseCase;
import utils.Waiters;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Тест кейс по ТЗ
 */
public class TestFirstCase extends BaseCase {
    PracticeForm practiceForm;

    @Test()
    @Description("Заполнение и отправка формы")
    public void userCanFillAndSubmitPracticeFrom() throws InterruptedException {
        practiceForm = new PracticeForm(driver);
        String firstName = "firstName";
        String lastname = "lastname";
        String mail = "goro4@mail.ru";
        String gender = "Male";
        String phone = "1234567890";
        String dateTime = "31";
        String month = "March";
        String year = "1982";
        String subjects = "English, Maths";
        String hobbies = ""; // в тз по хобби ничего не было
        String path = "src/test/resources/";
        String nameFiles = "jpeg.jpg";
        String filePath = path + nameFiles;
        String address = "currentAddress";
        String state = "NCR";
        String city = "Noida";

        Map<String, String> storage = Map.ofEntries(
                Map.entry("Student Name", firstName + " " + lastname),
                Map.entry("Student Email", mail),
                Map.entry("Gender", gender),
                Map.entry("Mobile", phone),
                Map.entry("Date of Birth", dateTime + " " + month + "," + year),
                Map.entry("Subjects", subjects),
                Map.entry("Hobbies", hobbies),
                Map.entry("Picture", nameFiles),
                Map.entry("Address", address),
                Map.entry("State and City", state + " " + city)
        );
        File file = new File(new File(filePath).getAbsolutePath());
        String absolutePath = file.getAbsolutePath();
        practiceForm.fillFirstName(firstName)
                .fillLastName(lastname)
                .fillMail(mail)
                .chooseGender(gender)
                .fillMobile(phone)
                .fillDate(year, month, dateTime)
                .fillSubjects(subjects)
                .uploadPicture(absolutePath)
                .fillAddress(address)
                .fillState(state)
                .fillCity(city)
                .submitForm();
//TODO сравение как билдер

        Waiters.waitVisibilityElement(practiceForm.modal, wait);
        String text = practiceForm.modal.getText();
        String answer = "Thanks for submitting the form";
//        1. Появилось всплывающее окно с заголовком Thanks for submitting the form
        boolean isGood = text.equals(answer);
        Assert.assertTrue(isGood);

        practiceForm.comparisonValues(storage);
    }
}

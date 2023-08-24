package cases;

import cases.base.BaseCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PracticeForm;
import utils.Waiters;

import java.util.List;
import java.util.Map;

public class TestFirstCase extends BaseCase {
    PracticeForm practiceForm;

    @Test()
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

        String path = "C:\\Hobby\\Git\\SDET\\SDET2023\\src\\test\\resources\\";
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
        practiceForm.fillForm(firstName, lastname, mail, gender, phone,
                filePath, dateTime, month, year, subjects, address, state, city);
        Waiters.waitVisibilityElement(practiceForm.modal, wait);
        String text = practiceForm.modal.getText();
        String answer = "Thanks for submitting the form";
//        1. Появилось всплывающее окно с заголовком Thanks for submitting the form
        boolean isGood = text.equals(answer);
        Assert.assertTrue(isGood);
//        2. В таблице на окне отображаются введенные ранее значения
        List<WebElement> listCell = driver.findElements(By.xpath("//table[contains(@class,'table ')]//td")); // 20
        String standard = "";
        for (int i = 0; i < listCell.size(); i++) {
            if (i % 2 == 0) {
                String key = listCell.get(i).getText();
                standard = storage.get(key);
            } else {
                String value = listCell.get(i).getText();
                Assert.assertEquals(standard, value);
            }
        }
     }
}

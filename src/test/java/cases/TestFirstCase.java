package cases;

import cases.base.BaseCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PracticeForm;
import utils.Waiters;

public class TestFirstCase extends BaseCase {
    PracticeForm practiceForm;

    @Test()
    public void userCanFillAndSubmitPracticeFrom() throws InterruptedException {
        practiceForm = new PracticeForm(driver);
        String filePath = "C:\\Hobby\\Git\\SDET\\SDET2023\\src\\test\\resources\\jpeg.jpg";
        String dateTime = "31";
        practiceForm.fillForm("first", "last", "goro4@mail.ru", "1234567890", filePath,dateTime);
        Waiters.waitVisibilityElement(practiceForm.modal, wait);
        String text = practiceForm.modal.getText();
        String answer = "Thanks for submitting the form";
        boolean isGood = text.equals(answer);
        Assert.assertTrue(isGood);

    }
}

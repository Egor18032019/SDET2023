package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import pages.PracticeForm;
import tests.base.BaseCase;
import utils.Const;

import java.io.File;

/**
 * Тест кейс по ТЗ
 */
public class TestFirstCase extends BaseCase {
    PracticeForm practiceForm;

    @Test()
    @Owner("Egor")
    @Description("Заполнение и отправка формы")
    public void userCanFillAndSubmitPracticeFrom() {
        practiceForm = new PracticeForm(driver);
        File file = new File(new File(Const.filePath).getAbsolutePath());
        String absolutePath = file.getAbsolutePath();
        practiceForm.fillFirstName(Const.firstName)
                .fillLastName(Const.lastname)
                .fillMail(Const.mail)
                .chooseGender(Const.gender)
                .fillMobile(Const.phone)
                .fillDate(Const.year, Const.month, Const.dateTime)
                .fillSubjects(Const.subjects)
                .uploadPicture(absolutePath)
                .fillAddress(Const.address)
                .fillState(Const.state)
                .fillCity(Const.city)
                .submitForm();

        practiceForm.modalWindow();

        practiceForm.comparisonValues(Const.storage);
    }
}

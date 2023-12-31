package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import pages.practiceForm.Form;
import pages.practiceForm.ModalWindow;
import tests.base.BaseCase;
import utils.Const;

import java.io.File;

/**
 * Тест кейс по ТЗ
 */
public class TestFirstCase extends BaseCase {
    Form form;
    ModalWindow modalWindow;

    @Test()
    @Owner("Egor")
    @Description("Заполнение и отправка формы")
    public void userCanFillAndSubmitPracticeFrom() {
        form = new Form(driver, webDriverWait);
        File file = new File(new File(Const.filePath).getAbsolutePath());
        String absolutePath = file.getAbsolutePath();
        form.fillFirstName(Const.firstName)
                .fillLastName(Const.lastname)
                .fillMail(Const.mail)
                .chooseGender(Const.gender)
                .fillMobile(Const.phone)
                .fillDate(Const.year, Const.month, Const.day)
                .fillSubjects(Const.subjects)
                .uploadPicture(absolutePath)
                .fillAddress(Const.address)
                .fillState(Const.state)
                .fillCity(Const.city)
                .submitForm();

        modalWindow = new ModalWindow(driver, webDriverWait);
        modalWindow.checkingTitle(Const.answer)
                   .comparisonValues(Const.storage);
    }
}

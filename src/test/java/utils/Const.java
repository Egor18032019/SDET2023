package utils;

import java.util.Map;

public class Const {
    public static final String urlMain = "https://demoqa.com/automation-practice-form";
    public static final String pathGoogleDriver = "src/test/resources/chromedriver.exe";
    public static final String firstName = "Egor";
    public static final String lastname = "Viktorovich";
    public static final String mail = "name@example.com";
    public static final String gender = "Male";
    public static final String phone = "1234567890";
    public static final String day = "31";
    public static final String answer = "Thanks for submitting the form";
    public static final String month = "March";
    public static final String year = "1982";
    public static final String subjects = "English, Maths";
    public static final String hobbies = ""; // в тз по хобби ничего не было
    public static final String path = "src/test/resources/";
    public static final String nameFiles = "jpeg.jpg";
    public static final String filePath = path + nameFiles;
    public static final String address = "currentAddress";
    public static final String state = "NCR";
    public static final String city = "Noida";
    public static Map<String, String> storage = Map.ofEntries(
            Map.entry("Student Name", firstName + " " + lastname),
            Map.entry("Student Email", mail),
            Map.entry("Gender", gender),
            Map.entry("Mobile", phone),
            Map.entry("Date of Birth", day + " " + month + "," + year),
            Map.entry("Subjects", subjects),
            Map.entry("Hobbies", hobbies),
            Map.entry("Picture", nameFiles),
            Map.entry("Address", address),
            Map.entry("State and City", state + " " + city)
    );
}

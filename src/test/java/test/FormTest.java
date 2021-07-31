package test;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import test.TestBase;

@Tag("properties")
public class FormTest  extends TestBase {
    RegistrationPage registrationPage= new RegistrationPage();


    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String gender = "Other";
    String phone = faker.numerify("##########");
    String day = "01";
    String month = "November";
    String year = "1995";
    String subject = "Maths";
    String picture = "file.jpeg";
    String hobbies = "Reading";
    String address = faker.address().fullAddress();
    String state = "Haryana";
    String city = "Panipat";




    @Test
    void positiveFillTest() {
        registrationPage.openPage();
        //Заполнение полей
        registrationPage.typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .chooseGender(gender)
                .typePhone(phone)
                .setBirthDate(day,month,year)
                .chooseSubject(subject)
                .uploadPicture(picture)
                .chooseHobbies(hobbies)
                .typeAddress(address)
                .chooseState(state)
                .chooseCity(city);


        registrationPage.submitForm();


        //Проверка данных
        registrationPage.checkResults(firstName+ " " +lastName )
                .checkResults(email)
                .checkResults(gender)
                .checkResults(phone)
                .checkResults(day+ " " +month + "," + year)
                .checkResults(subject)
                .checkResults(picture)
                .checkResults(hobbies)
                .checkResults(address)
                .checkResults(state + " " + city);

        registrationPage.closePopup();
        registrationPage.checkClosePopup();


    }
}
package test;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class FormTest extends TestBase{
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
    void successfulFillFormTest() {
        step("Open students registration form", () -> {
            open("https://demoqa.com/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Fill students registration form", () -> {
            step("Fill common data", () -> {
                $("#firstName").val(firstName);
                $("#lastName").val(lastName);
                $("#userEmail").val(email);
                $("#genterWrapper").$(byText(gender)).click();
                $("#userNumber").val(phone);
            });
            step("Set date", () -> {
                $("#dateOfBirthInput").clear();
                $(".react-datepicker__month-select").selectOption(month);
                $(".react-datepicker__year-select").selectOption(year);
                $(".react-datepicker__day--0" + day).click();
            });
            step("Set subjects", () -> {
                $("#subjectsInput").val(subject);
                $("#subjectsInput").setValue(subject).pressEnter();
            });
            step("Set hobbies", () -> {
                $("#hobbiesWrapper").$(byText(hobbies)).click();
            });
            step("Upload image", () ->
                    $("#uploadPicture").uploadFromClasspath(picture));
            step("Set address", () -> {
                $("#currentAddress").val(address);
                $("#state").scrollTo().click();
                $("#stateCity-wrapper").$(byText(state)).click();
                $("#city").click();
                $("#stateCity-wrapper").$(byText(city)).click();
            });
            step("Submit form", () ->
                    $("#submit").click());
        });

        step("Verify successful form submit", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $x("//td[text()='Student Name']").parent().shouldHave(text(firstName + " " + lastName));
            $x("//td[text()='Student Email']").parent().shouldHave(text(email));
            $x("//td[text()='Gender']").parent().shouldHave(text(gender));
            $x("//td[text()='Mobile']").parent().shouldHave(text(phone));
            $x("//td[text()='Date of Birth']").parent().shouldHave(text(day + " " + month + "," + year));
            $x("//td[text()='Subjects']").parent().shouldHave(text(subject));
            $x("//td[text()='Hobbies']").parent().shouldHave(text(hobbies));
            $x("//td[text()='Picture']").parent().shouldHave(text(picture));
            $x("//td[text()='Address']").parent().shouldHave(text(address));
            $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));
        });
    }
}
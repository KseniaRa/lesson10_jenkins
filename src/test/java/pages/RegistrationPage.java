package pages;
import components.Calendar;


import java.io.File;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;


public class RegistrationPage {

    private Calendar calendar= new Calendar();

    public RegistrationPage openPage(){

        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage typeFirstName(String firstName) {
        $("#firstName").val(firstName);
        return this;
    }

    public RegistrationPage typeLastName(String lastName) {
        $("#lastName").val(lastName);
        return this;
    }

    public RegistrationPage typeEmail(String email) {
        $("#userEmail").val(email);

        return this;
    }

    public RegistrationPage chooseGender(String gender){
        $(format("[name=gender][value=%s]", gender)).parent().click();
        return this;
    }


    public RegistrationPage typePhone(String phone){
        $("#userNumber").setValue(phone);
        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year){
        calendar.setDate(day,month, year);
        return this;
    }

    public RegistrationPage chooseSubject(String subject){
        $("#subjectsInput").setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage uploadPicture(String picture){
        $("#uploadPicture").uploadFile(new File("src/test/resources/" + picture));
        return this;
    }

    public RegistrationPage chooseHobbies(String hobbies){
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        return this;
    }

    public RegistrationPage typeAddress( String address){
        $("#currentAddress").setValue(address);
        return this;
    }

    public RegistrationPage chooseState(String state){
        $("#react-select-3-input").setValue(state).pressEnter();
        return this;
    }

    public RegistrationPage chooseCity(String city){
        $("#react-select-4-input").setValue(city).pressEnter();
        return this;
    }

    public RegistrationPage submitForm(){
        $("#submit").scrollTo().click();
        return this;
    }

    public RegistrationPage checkResults(String results){
        $(".table-responsive").shouldHave(text(results));
        return this;
    }

    public RegistrationPage closePopup(){
        $("#closeLargeModal").click();
        return this;
    }

    public RegistrationPage checkClosePopup(){
        $("#example-modal-sizes-title-lg").should(disappear);
        return this;
    }


}


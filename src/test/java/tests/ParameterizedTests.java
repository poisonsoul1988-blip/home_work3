package tests;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedTests extends TestBase{

//    @CsvSource(value = {
//            "user , 43434",
//            "user3 ,1234tes!T"
//    })
    @CsvFileSource(resources = "/test_data/errorTextShouldAppearWhenCredsAreInvalid.csv")
    @ParameterizedTest(name = "При вводе неверных логина и пароля появяется тескст с ошибкой")
    @Tag("Urgent")
    void errorTextShouldAppearWhenCredsAreInvalid(String username, String password) {
        open("/login");
        $("#newUser").click();
        $("#firstname").setValue("A");
        $("#lastname").setValue("B");
        $("#userName").setValue("user");
        $("#password").setValue("1234tes!T");
        $("#register").click();
        $("#gotologin").click();
        $("#userName").setValue(username);
        $("#password").setValue(password);
        $("#login").click();
        $("#name").shouldHave(text("Invalid username or password!"));

    }

    @ValueSource(strings = {
            "Git Pocket Guide", "Designing Evolvable Web APIs with ASP.NET"
    })
    @ParameterizedTest(name = "При поиске книги {0} в результате отображается книга {0}")
    @Tag("BLOCKER")
    void searchResultShouldContainExpectedBook(String searchQuery) {
        open("/books");
        $("#searchBox").setValue(searchQuery).pressEnter();
        //$$("table tbody tr").shouldHave(size(1));;
        $("table tbody tr")
                .shouldHave(text(searchQuery));
    }

}

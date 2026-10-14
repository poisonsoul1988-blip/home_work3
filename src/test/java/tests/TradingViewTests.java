package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tests.data.Language;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TradingViewTests {

    static Stream<Arguments> selenideSiteShouldDisplayCorrectButtons () {
        return Stream.of(
                Arguments.of(
                        Language.English,
                        List.of("Products","Community","Markets","Brokers","More","Get started")),
                Arguments.of(
                        Language.Русский,
                        List.of("Продукты","Сообщество","Рынки","Брокеры","Ещё","Регистрация"))
        );
    }

    @MethodSource
    @ParameterizedTest
    void selenideSiteShouldDisplayCorrectButtons(Language language, List<String> expectedButtons){
        open("https://tradingview.com/");
        $("button[aria-label='Open language menu']").click();
        $(byText(language.name())).click();
        $$(".tv-header a").filter(visible).filter(not(empty))
                .shouldHave(texts(expectedButtons));


    }
}

package guru.qa;

import com.codeborne.selenide.Configuration;
import guru.qa.seleniumLanguageConfig.LanguageConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LanguageConfigForSeleniumTest {

    @BeforeAll
    static void beforeTestsStart() {
        Configuration.browserSize = "1928x1080";
        Configuration.baseUrl = "https://www.selenium.dev";
        Configuration.pageLoadStrategy = "eager";
    }
    static Stream<Arguments> homePageShouldDisplayProperTabsInNevMenuTest() {
        return Stream.of(
                Arguments.of(
                        LanguageConfiguration.日本語,
                        List.of(
                                "About\n" +
                                        "Downloads\n" +
                                        "Documentation\n" +
                                        "Projects\n" +
                                        "Support\n" +
                                        "Blog\n" +
                                        "日本語\n"
                        )
                ),
                Arguments.of(
                        LanguageConfiguration.中文简体,
                        List.of(
                                "About\n" +
                                        "Downloads\n" +
                                        "Documentation\n" +
                                        "Projects\n" +
                                        "Support\n" +
                                        "Blog\n" +
                                        "中文简体\n"
                        )
                )
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Home page should have propoer tabs in nav menu")
    @Tag("WEB")
    void homePageShouldDisplayProperTabsInNevMenuTest(LanguageConfiguration languageConfiguration, List<String> expectedValue) throws Exception {
        open("/");
        $("li[class='nav-item dropdown d-none d-lg-block']").click();
        $("ul[class='dropdown-menu show']").$(byText(languageConfiguration.description)).click();
        $$("#main_navbar").shouldHave(texts(expectedValue));
    }
}

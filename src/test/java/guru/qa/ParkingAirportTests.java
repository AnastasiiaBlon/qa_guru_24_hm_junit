package guru.qa;

import guru.qa.parkMenuData.MenuData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParkingAirportTests extends BrowserConfiguration {

    @Test
    @Tag("BLOCKER")
    @DisplayName("Parking banner is shown on 'Parking' page")
    void parkingBannerIsShownTest() {
        open("/park");
        $("button[class='css-ic4j90 ejs5oi80']").$(byText("Accept and Continue")).click();
        $("h2[class='css-3rgdem enplxzz1']").shouldHave(text("Prebook Parking"));
    }

    @ValueSource(strings = {
            "starbucks", "shops"
    })
    @Tag("BLOCKER")
    @ParameterizedTest(name = "Results list should be shown for search request {0}")
    void resultsListShouldBeShownForSearchRequestTest(String searchQuery) {
        open("/park");
        $("button[class='css-ic4j90 ejs5oi80']").$(byText("Accept and Continue")).click();
        $("button[class='css-yg9yyq e169qrcj1']").click();
        $("input[name='unified-search']").setValue(searchQuery).pressEnter();

        $$("div[class='css-1eibu2r etymn1f0']").shouldBe(sizeGreaterThan(0));
    }

    @CsvFileSource(resources = "/testData/testDataMenuTabs.csv")
    @ParameterizedTest(name = "Navigation menu should contain tabs {0}")
    @Tag("BLOCKER")
    void navigationMenuShouldContainTabsTest(String expectedTab) {
        open("/park");
        $("div[class='css-1al3ftz efazz650']")
                .shouldHave(text(expectedTab));
    }

    static Stream<Arguments> correctValuesInTabsDropdownListsTest() {
        return Stream.of(
                Arguments.of(
                        MenuData.PARK,
                        "Park",
                        List.of(
                                "Book Parking",
                                "Parking Products",
                                "Parking FAQs",
                                "Parking Availability",
                                "Refunds & Payments"
                        )
                ),
                Arguments.of(
                        MenuData.EXPLORE,
                        "Explore",
                        List.of(
                                "In the Terminals",
                                "Map",
                                "Transportation",
                                "Plan",
                                "Security Wait Times",
                                "Construction at DFW"
                        )
                )
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Dropdowns of nav menu tabs should have proper values in the lists")
    @Tag("WEB")
    void correctValuesInTabsDropdownListsTest(MenuData menuData, String text, List<String> expectedValue) {
        open("/park");
        $("div[class='css-1al3ftz efazz650']").$(byText(menuData.description)).hover();
    }

}

package guru.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParkingAirportTests extends BrowserConfiguration {

    @Test
    @Tag("BLOCKER")
    @DisplayName("Parking banner is shown on 'Parking' page")
    void parkingBannerIsShown() {
        open("/park");
        $("button[class='css-ic4j90 ejs5oi80']").$(byText("Accept and Continue")).click();
        $("h2[class='css-3rgdem enplxzz1']").shouldHave(text("Prebook Parking"));
    }

    @ValueSource(strings = {
            "starbucks", "shops"
    })
    @Tag("BLOCKER")
    @ParameterizedTest(name = "Results list should be shown for search request {0}")
    void resultsListShouldBeShownForSearchRequest(String searchQuery) {
        open("/park");
        $("button[class='css-ic4j90 ejs5oi80']").$(byText("Accept and Continue")).click();
        $("button[class='css-yg9yyq e169qrcj1']").click();
        $("input[name='unified-search']").setValue(searchQuery).pressEnter();

        $$("div[class='css-1eibu2r etymn1f0']").shouldBe(sizeGreaterThan(0));
    }

    @CsvFileSource(resources = "/testData/testDataMenuTabs.csv")
    @ParameterizedTest(name = "Navigation menu should contain tabs {0}")
    @Tag("BLOCKER")
    void navigationMenuShouldContainTabs(String expectedTab) {
        open("/park");
        $("div[class='css-1al3ftz efazz650']")
                .shouldHave(text(expectedTab));
    }

}

package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class BrowserConfiguration {
    @BeforeAll
    static void beforeTestsStart() {
        Configuration.browserSize = "1928x1080";
        Configuration.baseUrl = "https://www.dfwairport.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void afterEachTest() {
        Selenide.closeWebDriver();
    }
}

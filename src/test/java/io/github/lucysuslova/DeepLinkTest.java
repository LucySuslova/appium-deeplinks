package io.github.lucysuslova;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


public class DeepLinkTest {

    @BeforeAll
    public void setUp() {
        closeWebDriver();
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.browser = MobileDriver.class.getName();
        open();
    }

    @Test
    public void openDeepLinkTest() {
        // do something before

        new DeepLinkUtils().openDeepLinkUrl("login/tom/mypassword");

        // assertions, etc.

    }

}

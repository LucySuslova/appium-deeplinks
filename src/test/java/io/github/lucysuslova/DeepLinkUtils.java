package io.github.lucysuslova;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DeepLinkUtils {

    private static final String PREFIX = "theapp://";

    private By urlButton = MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' && name CONTAINS 'URL'");
    private By urlField = MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeTextField' && name CONTAINS 'URL'");
    private By openButton = MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' && name CONTAINS 'Open'");

    public void openDeepLinkUrl(String url) {
        if (isAndroid()) openAndroidDeepLinkUrl(url);
        else openIosDeepLinkUrl(url);
    }

    private void openIosDeepLinkUrl(String url) {
        driver().terminateApp("io.cloudgrey.the-app");
        driver().activateApp("com.apple.mobilesafari");

        if (!$(urlButton).exists()) driver().getKeyboard();
        $(urlButton).shouldBe(Condition.visible).click();

        $(urlField).sendKeys(String.format("%s%s\uE007", PREFIX, url));
        $(openButton).shouldBe(Condition.visible).click();
    }

    private void openAndroidDeepLinkUrl(String url) {
        driver().terminateApp("io.cloudgrey.the_app");

        HashMap<String, Object> args = new HashMap<>();
        args.put("url", PREFIX + url);
        args.put("package", "io.cloudgrey.the_app");

        driver().executeScript("mobile: deepLink", args);
    }

    @SuppressWarnings("unchecked")
    private AppiumDriver<? extends MobileElement> driver() {
        return (AppiumDriver<? extends MobileElement>) getWebDriver();
    }

    private boolean isAndroid() {
        return driver().getCapabilities().getCapability("platformName").toString()
                .equalsIgnoreCase(Platform.ANDROID.toString());
    }
}

package io.github.lucysuslova;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class MobileDriver implements WebDriverProvider {

    @Override
    public AppiumDriver<? extends MobileElement> createDriver(DesiredCapabilities desiredCapabilities) {
        return switch (System.getProperty("platform")) {
            case "ios" -> getIosDriver();
            case "android" -> getAndroidDriver();
            default -> throw new IllegalArgumentException("Platform is not set or recognized: " + System.getProperty("platform"));
        };
    }

    @SneakyThrows
    private AndroidDriver getAndroidDriver() { //provide your Android device and app
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.APP, "PATH_TO_YOUR_APP");

        return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @SneakyThrows
    private IOSDriver getIosDriver() { //provide your iOS device and app
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.3");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
        capabilities.setCapability(MobileCapabilityType.APP, "PATH_TO_YOUR_APP");

        return new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
}

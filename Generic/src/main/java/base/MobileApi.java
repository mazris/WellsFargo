package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileApi {

    public static AppiumDriver appiumDriver = null;
    public static String platform = null;

    @Parameters({"OS", "deviceName", "version"})
    @BeforeMethod
    public static AppiumDriver getDriver(String OS, String deviceName, String version) throws IOException {
        platform = OS;
        DesiredCapabilities cap = new DesiredCapabilities();
        if (OS.equalsIgnoreCase("Android")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
            capabilities.setCapability(MobileCapabilityType.APP, "/Users/mac/Documents/weekdays/BootCamp/Generic/src/main/resources/New York Post for Tablet_v3.1.495-GooglePlay_apkpure.com.apk");
            appiumDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

        } else {
            cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
            cap.setCapability(MobileCapabilityType.APP_PACKAGE, "com.target");
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            cap.setCapability(MobileCapabilityType.APP_PACKAGE, "com.target");
            if (appiumDriver == null) {
                appiumDriver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), cap);
            }
        }
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return appiumDriver;
    }
}

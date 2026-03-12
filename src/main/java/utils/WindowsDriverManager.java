package utils;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class WindowsDriverManager {
    private static WindowsDriver<WindowsElement> driver;

    public static WindowsDriver<WindowsElement> getDriver(String appId) throws Exception {
        if (driver == null) {
            DesiredCapabilities cap = new DesiredCapabilities();
           
            cap.setCapability("platformName", "Windows");
            cap.setCapability("deviceName", "WindowsPC");
            cap.setCapability("app", "Root");
            cap.setCapability("app", appId);
            driver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        }
        return driver;
    }

    public static WindowsDriver<WindowsElement> getDriverInstance() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

package Pages;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import locators.Locators;
import utils.WaitHelper;
import utils.ScreenshotUtil;

public class Login {

    private WindowsDriver<WindowsElement> driver;
    private WaitHelper wait;

    public Login(WindowsDriver<WindowsElement> driver) {
        this.driver = driver;
        wait = new WaitHelper(driver, 20);
    }

    public void enterUsername(String user) {
        WindowsElement e = wait.waitForElementByAccessibilityId(Locators.USERNAME);
        e.click();
        e.clear();
        e.sendKeys(user);

        // 📸 Screenshot
        ScreenshotUtil.takeScreenshot(driver, "Login - Entered Username");
    }

    public void enterPassword(String pwd) {
        WindowsElement e = wait.waitForElementByAccessibilityId(Locators.PASSWORD);
        e.click();
        e.clear();
        e.sendKeys(pwd);

        // 📸 Screenshot
        ScreenshotUtil.takeScreenshot(driver, "Login - Entered Password");
    }

    public void clickLogin() {
        wait.waitForElementByAccessibilityId(Locators.LOGIN_BUTTON).click();

        // 📸 Screenshot
        ScreenshotUtil.takeScreenshot(driver, "Login - Clicked Login Button");
    }

    public void clickOKIfPresent() {
        try {
            wait.waitForElementByName(Locators.OK_BUTTON).click();

            // 📸 Screenshot
            ScreenshotUtil.takeScreenshot(driver, "Login - Clicked OK Popup");
        } catch (Exception ignore) {
            // If element not found, OK popup not present
        }
    }

    public void goToHome() {
        wait.waitForElementByAccessibilityId(Locators.HOME_NAV).click();

        // 📸 Screenshot
        ScreenshotUtil.takeScreenshot(driver, "Login - Navigated to Home Screen");
    }
}

package Pages;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import locators.Locators;
import utils.WaitHelper;
import utils.ScreenshotUtil;

public class Associate {

    private WindowsDriver<WindowsElement> driver;
    private WaitHelper wait;

    public Associate(WindowsDriver<WindowsElement> driver) {
        this.driver = driver;
        wait = new WaitHelper(driver, 10);
    }

    public void inputStore(String storeCode) {
        WindowsElement e = wait.waitForElementByAccessibilityId(Locators.INPUT);
        e.click();
        e.clear();
        e.sendKeys(storeCode);

        ScreenshotUtil.takeScreenshot(driver, "Associate - " + storeCode);
    }

    public void selectEmailOption() {
        wait.waitForElementByName(Locators.EMAIL_OPTION_NAME).click();
        ScreenshotUtil.takeScreenshot(driver, "Associate - Selected Email Option");
    }

    public void clickApply() {
        wait.waitForElementByName(Locators.APPLY_BUTTON).click();
        ScreenshotUtil.takeScreenshot(driver, "Associate - Clicked Apply");
    }
}

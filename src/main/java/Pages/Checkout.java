package Pages;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import locators.Locators;
import utils.WaitHelper;
import utils.ScreenshotUtil;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Keys;

public class Checkout {

    private WindowsDriver<WindowsElement> driver;
    private WaitHelper wait;
    private Robot robot;


    public Checkout(WindowsDriver<WindowsElement> driver) {
        this.driver = driver;
        wait = new WaitHelper(driver, 10);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("Robot init failed", e);
        }
    }
    

    public void clickCheckout() {
        wait.waitForElementByXPath(Locators.CHECKOUT).click();

        // 📸 Screenshot
        ScreenshotUtil.takeScreenshot(driver, "Checkout - Clicked Checkout");
    }
    
    /* CASH */

    public void clickCash() {
        wait.waitForElementByName(Locators.CASH).click();

        // 📸 Screenshot
        ScreenshotUtil.takeScreenshot(driver, "Checkout - Clicked Cash Payment");
    }

    public void enterAmountAndPay(String amount) {
        WindowsElement e = wait.waitForElementByAccessibilityId(Locators.INPUT);
        e.click();
        e.clear();
        e.sendKeys(amount);

        // Short pause for UI stability
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}

        // 📸 Screenshot after entering amount
        ScreenshotUtil.takeScreenshot(driver, "Checkout - Entered Amount: " + amount);

        e.sendKeys(Keys.ENTER);
        wait.waitForElementByName(Locators.PAY_BUTTON).click();

        // 📸 Screenshot after clicking Pay
        ScreenshotUtil.takeScreenshot(driver, "Checkout - Clicked Pay");
    }

    public void clickBypass() {
        wait.waitForElementByName(Locators.BYPASS).click();

        // 📸 Screenshot
        ScreenshotUtil.takeScreenshot(driver, "Checkout - Clicked Bypass");
    }

    public void clickCloseDrawer() {
        wait.waitForElementByName(Locators.CLOSE_DRAWER).click();

        // 📸 Screenshot
        ScreenshotUtil.takeScreenshot(driver, "Checkout - Closed Drawer");
    }

    public void sendEmail(String email) throws AWTException {
        WindowsElement e = wait.waitForElementByAccessibilityId(Locators.INPUT);
        e.click();
        e.clear();
        e.sendKeys(email);
        
        // 📸 Screenshot
        ScreenshotUtil.takeScreenshot(driver, "Checkout - Entered Email: " + email);
       
    }
    public void pageDown() {
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }

    public void printEmail() {
        wait.waitForElementByName(Locators.PRINT_AND_EMAIL).click();

        // 📸 Screenshot
        ScreenshotUtil.takeScreenshot(driver, "Checkout - Clicked Print + Email");
    }
    
    public void refund() throws InterruptedException {
         Thread.sleep(1000);
        robot.mouseMove(806, 491);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

   
        ScreenshotUtil.takeScreenshot(driver, "Refund_Completed");
    }
    
}

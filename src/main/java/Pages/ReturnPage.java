package Pages;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import locators.Locators;
import utils.WaitHelper;
import utils.ScreenshotUtil;

import java.awt.*;
import java.awt.event.InputEvent;

public class ReturnPage {

    private WindowsDriver<WindowsElement> driver;
    private WaitHelper wait;
    private Robot robot;

    public ReturnPage(WindowsDriver<WindowsElement> driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver, 20);

        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("Robot initialization failed", e);
        }
    }

    public void startReturn() {
        wait.waitForElementByName(Locators.START_RETURN).click();
        wait.waitForElementByName(Locators.TRANSACTIONS).click();
    }

    public void selectdropdownUsingRobot() throws InterruptedException {
    	/*Thread.sleep(1000);
       robot.mouseMove(445, 533);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);*/
    	wait.waitForElementByXPath(Locators.Reason).click();
        ScreenshotUtil.takeScreenshot(driver, "Return reason dropdown");
    }

    public void selectReturnReasonAndApply() {
        wait.waitForElementByName(Locators.COLOR_ISSUE).click();
        wait.waitForElementByName(Locators.APPLY_BUTTON).click();

        ScreenshotUtil.takeScreenshot(driver, "Return_Applied");
    }
    public void mainMenu() throws AWTException, InterruptedException {
        Thread.sleep(2000);
       Robot rb = new Robot();
       Thread.sleep(1000);

       // Perform Cancel Click through Robot
       rb.mouseMove(681, 671);      
       rb.mousePress(InputEvent.BUTTON1_DOWN_MASK);
       
       rb.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

       Thread.sleep(1000);

       // 📸 Screenshot after clicking Cancel
       ScreenshotUtil.takeScreenshot(driver, "Select Cart Mode");
   }
    public void selectCartMode() {
        wait.waitForElementByName(Locators.Cart_Mode).click();
   

        ScreenshotUtil.takeScreenshot(driver, "Exchange item");
    }
}

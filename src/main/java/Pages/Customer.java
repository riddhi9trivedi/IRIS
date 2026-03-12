package Pages;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import locators.Locators;
import utils.WaitHelper;
import utils.ScreenshotUtil;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class Customer {

    private WindowsDriver<WindowsElement> driver;
    private WaitHelper wait;

    public Customer(WindowsDriver<WindowsElement> driver) {
        this.driver = driver;
        wait = new WaitHelper(driver, 10);
    }

    public void cancel() throws AWTException, InterruptedException {
         Thread.sleep(2000);
        Robot rb = new Robot();
        Thread.sleep(1000);

        // Perform Cancel Click through Robot
        rb.mouseMove(982, 204);
        rb.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        rb.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        Thread.sleep(1000);

        // 📸 Screenshot after clicking Cancel
        ScreenshotUtil.takeScreenshot(driver, "Customer - Clicked Cancel");
    }
}

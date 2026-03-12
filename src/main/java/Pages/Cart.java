package Pages;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import locators.Locators;
import utils.WaitHelper;
import utils.ScreenshotUtil;
import org.openqa.selenium.Keys;

public class Cart {

    private WindowsDriver<WindowsElement> driver;
    private WaitHelper wait;

    public Cart(WindowsDriver<WindowsElement> driver) {
        this.driver = driver;
        wait = new WaitHelper(driver, 20);
    }

    public void sendEnterOnInput(String textToType) throws InterruptedException {

        WindowsElement e = wait.waitForElementByAccessibilityId(Locators.INPUT);
        e.click();
        e.clear();
        e.sendKeys(textToType);

        // Small delay to mimic natural typing
        try { Thread.sleep(300); } catch (InterruptedException ex) {}

        e.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        // 📸 Screenshot after adding item to cart
        ScreenshotUtil.takeScreenshot(driver, "Cart - Entered POS Input: " + textToType);
    }
    
    
    public void Transaction_Summary() {
    	
    	WindowsElement summary = wait.waitForElementByAccessibilityId(Locators.Summary);
        summary.click();
        
        
    	
    	
    }
    
}

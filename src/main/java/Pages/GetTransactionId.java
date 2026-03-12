package Pages;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import locators.Locators;
import utils.WaitHelper;
import utils.ScreenshotUtil;
import org.openqa.selenium.WebElement;

public class GetTransactionId {

    private WindowsDriver<WindowsElement> driver;
    private WaitHelper wait;

    public GetTransactionId(WindowsDriver<WindowsElement> driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver, 20);
    }

    /**
     * Fetch Transaction Order Number
     * @throws InterruptedException 
     */
    public String fetchTransactionOrder(String orderHint) throws InterruptedException {

        // Click TRANSACTIONS
        wait.waitForElementByName(
                Locators.TRANSACTIONS_MENU);
        wait.waitForElementByName(
                Locators.TRANSACTIONS_MENU).click();

        // Click Order Number
        wait.waitForElementByName(
                Locators.ORDER_NUMBER_MENU);
        wait.waitForElementByName(
                Locators.ORDER_NUMBER_MENU).click();
        Thread.sleep(2000);

        // Dynamic Order Number
        String xpath = String.format(
                Locators.ORDER_NUMBER_XPATH, orderHint);

        WebElement orderNumber =
                wait.waitForElementByXPath(xpath);

        String order = orderNumber.getAttribute("Name");
        System.out.println("Transaction Order Number: " + order);
        
        wait.waitForElementByName(
                Locators.TRANSACTIONS_MENU).click();

        ScreenshotUtil.takeScreenshot(driver,
                "Transaction_Order_" + order);

        return order;
    }
}

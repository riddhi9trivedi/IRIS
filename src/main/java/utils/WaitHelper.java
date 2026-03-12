package utils;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
    private WindowsDriver<WindowsElement> driver;
    private WebDriverWait wait;

    public WaitHelper(WindowsDriver<WindowsElement> driver, int timeoutSeconds) {
        this.driver = driver;
        wait = new WebDriverWait(driver, timeoutSeconds);
    }

    public WindowsElement waitForElementByAccessibilityId(String accessibilityId) {
        // use XPath workaround for AutomationId
        String xpath = "//*[@AutomationId='" + accessibilityId + "']";
        return (WindowsElement) wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath(xpath))
        );
    }

  public WindowsElement waitForElementByName(String name) {
        return (WindowsElement) wait.until(
            ExpectedConditions.elementToBeClickable(By.name(name))
        );
    }
    public WindowsElement waitForElementByXPath(String xpath) {
        return (WindowsElement) wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(xpath)));
    }


    
    public WindowsElement waitForElementToBeClickableByClassName(String className) {
        return (WindowsElement) wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.className(className)));
    }
    
}

	


package Testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Data.TestData;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Test2 {
	
	public static WindowsDriver driver = null;
	 private String authToken; 
	

	@BeforeClass
	 public void setUp() throws Exception {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		String application_id = "com.manh.storeui_0ke3e0bm2cnf2!com.manh.storeui";
		cap.setCapability("app", application_id);
		cap.setCapability("platformName", "Windows");
		cap.setCapability("deviceName", "WindowsPC");
		
		driver = new WindowsDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	

	   
	
	@Test
	public void login() throws MalformedURLException, InterruptedException, Exception {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		String application_id = "com.manh.storeui_0ke3e0bm2cnf2!com.manh.storeui";
		cap.setCapability("app", application_id);
		cap.setCapability("platformName", "Windows");
		cap.setCapability("deviceName", "WindowsPC");
		
		driver = new WindowsDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(2000);

		WindowsElement userField = (WindowsElement) driver.findElementByAccessibilityId("username");
		userField.click();
		userField.sendKeys("strmgr_can");
		Thread.sleep(1000);

		WindowsElement passField = (WindowsElement) driver.findElementByAccessibilityId("password");
		passField.click();
		passField.sendKeys("Welcome21!");
		Thread.sleep(1000);

		driver.findElementByAccessibilityId("kc-login").click();
	
	driver.findElementByName("OK").click();
	

		driver.findElementByAccessibilityId("storeCartNextGen-ui-pos-home-l3").click();

		WindowsElement inputField = (WindowsElement) driver.findElementByAccessibilityId("input");
		inputField.click();
		inputField.sendKeys("100443");
		Thread.sleep(1000);
		driver.findElementByName("100443@corp.gdglobal.ca").click();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElementByName("Apply").click();
		Thread.sleep(1000);

		Robot rb = new Robot();
		Thread.sleep(1000);
		rb.mouseMove(982, 204);
		rb.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		rb.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(1000);

		WindowsElement anotherInput = (WindowsElement) driver.findElementByAccessibilityId("input");
		anotherInput.click();
		anotherInput.clear();
		anotherInput.sendKeys("0123456");
		Thread.sleep(500);
		//driver.findElementByXPath("//Button[@ClassName=\"button button-icon\"]").click(
		
	//	anotherInput.sendKeys(Keys.ENTER);
		//anotherInput.sendKeys("0123456");
		anotherInput.sendKeys(Keys.ENTER);
	///	driver.findElementByName("Close").click();
		Thread.sleep(2000);

		rb.mouseMove(975, 404);
		rb.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		rb.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(1000);
	
	driver.findElementByName("Add Item Discount").click();
	driver.findElementByName("Percent").click();

	WindowsElement inputField2 = (WindowsElement) driver.findElementByAccessibilityId("input");
/*	inputField2.click();
	inputField2.sendKeys("200");
      rb.keyPress(KeyEvent.VK_PAGE_DOWN);
      rb.keyRelease(KeyEvent.VK_PAGE_DOWN);
  	rb.mouseMove(473, 542);
	rb.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	rb.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
     // driver.findElement(By.xpath("//input[contains(@class,'select__display-input')]")).click();
      Thread.sleep(1000);

      driver.findElementByName("Denim").click();
      driver.findElementByName("Apply").click();*/
	Thread.sleep(2000);
	
	
//------//
	
	//WindowsElement inputField2 = (WindowsElement) driver.findElementByAccessibilityId("input");
	inputField2.click();
	inputField2.sendKeys("51");
      rb.keyPress(KeyEvent.VK_PAGE_DOWN);
      rb.keyRelease(KeyEvent.VK_PAGE_DOWN);
  	rb.mouseMove(473, 542);
	rb.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	rb.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
     // driver.findElement(By.xpath("//input[contains(@class,'select__display-input')]")).click();
      Thread.sleep(1000);

      driver.findElementByName("Denim").click();
      driver.findElementByName("Apply").click();
	Thread.sleep(2000);
	
	
			String Exp = "The entered value exceeds the maximum discount for this item. 50% is allowed for this role.";
	WebElement act = driver.findElementByName(Exp);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	String Expected = "The entered value exceeds the maximum discount for this item. 50% is allowed for this role.";
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	String actual = act.getText();
	System.out.println(act);
	
	Assert.assertEquals(actual, Expected);
	Thread.sleep(2000);
	driver.findElementByName("Close").click();
	inputField2.clear();
	inputField2.sendKeys("2000");
	  driver.findElementByName("Apply").click();
		Thread.sleep(2000);
	
		driver.findElementByName("Checkout").click();
		Thread.sleep(1000);
		driver.findElementByName("Cash").click();
		Thread.sleep(1000);

		WindowsElement amountInput = (WindowsElement) driver.findElementByAccessibilityId("input");
		amountInput.click();
		amountInput.clear();
		amountInput.sendKeys("4000");
		Thread.sleep(500);
		amountInput.sendKeys(Keys.ENTER);
		Thread.sleep(1000);

		driver.findElementByName("Pay").click();
		Thread.sleep(2000);
		driver.findElementByName("Bypass").click();
		Thread.sleep(2000);
		driver.findElementByName("Close Drawer").click();
		Thread.sleep(2000);

		
}}

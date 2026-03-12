package Testcases;

import org.testng.annotations.BeforeTest;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import Pages.Associate;
import Pages.Cart;
import Pages.Checkout;
import Pages.Customer;
import Pages.Login;
import groovy.time.Duration;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

import utils.ScreenshotUtil;
import utils.WindowsDriverManager;
import Data.TestData;

public class Test1 {

    public WindowsDriver<WindowsElement> driver;
    private Login loginPage;
    private Associate assoc;
    private Customer cust;
    private Cart cart;
    private Checkout checkout;
//    public static String bearerToken;


    @BeforeTest
    public void setup() throws Exception {
        driver = WindowsDriverManager.getDriver(TestData.APP_ID);
        driver.manage().window().maximize();
       
        loginPage = new Login(driver);
        assoc = new Associate(driver);
        cust = new Customer(driver);
        cart = new Cart(driver);
        checkout = new Checkout(driver);
    }

    @Test
    public void loginAndCheckoutFlow() throws Exception {
        loginPage.enterUsername(TestData.USERNAME);

        loginPage.enterPassword(TestData.PASSWORD);

        loginPage.clickLogin();
       // Thread.sleep(4000);
        //bearerToken = ApiTokenProvider.getBearerToken();
      //  System.out.println("Bearer token fetched successfully");
     

       loginPage.clickOKIfPresent();
        loginPage.goToHome();

        assoc.inputStore(TestData.ASSOCIATE);
       assoc.selectEmailOption();
       assoc.clickApply();

     cust.cancel();

        cart.sendEnterOnInput(TestData.POS_INPUT1);
Thread.sleep(1000);
        checkout.clickCheckout();
        checkout.clickCash();
        checkout.enterAmountAndPay(TestData.AMOUNT);
        checkout.clickBypass();
        checkout.clickCloseDrawer();
        checkout.sendEmail(TestData.EMAIL_ADDRESS);
        Robot rb = new Robot();
        rb.keyPress(KeyEvent.VK_PAGE_DOWN);
        rb.keyRelease(KeyEvent.VK_PAGE_DOWN);
      /*  String token = ApiTokenProvider.getBearerToken();
        String latestOrderId = OrderApiUtil.getLatestOrderId(token);

 /*       System.out.println("Latest Order ID: " + latestOrderId);*/
    checkout.printEmail();


        assoc.inputStore(TestData.ASSOCIATE);
        assoc.selectEmailOption();
        assoc.clickApply();

        cust.cancel();
        Thread.sleep(2000);
        driver.findElementByName("TRANSACTIONS").click();
        Thread.sleep(2000);
        driver.findElementByName("Order Number").click();
       Thread.sleep(2000);
        WebElement orderNumber =
        		driver.findElementByXPath("//*[contains(@Name, '010')]");
        		Thread.sleep(2000);
    String order=orderNumber.getAttribute("Name");  
    		System.out.println(orderNumber.getAttribute("Name"));
        Thread.sleep(2000);
        driver.findElementByName("Start A Return").click();
        driver.findElementByName("TRANSACTIONS").click();

        cart.sendEnterOnInput(order);
        cart.sendEnterOnInput(TestData.POS_INPUT1);
        
        Thread.sleep(1000);

        // Perform Cancel Click through Robot
        rb.mouseMove(501, 533);
        rb.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        rb.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        Thread.sleep(1000);
        driver.findElementByName("Color issue").click();
        driver.findElementByName("Apply").click();
        checkout.clickCheckout();
        Thread.sleep(1000);
        

        // Perform Cancel Click through Robot
        rb.mouseMove(811, 489);
        rb.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        rb.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
      

        driver.findElementByClassName("overflow-unset text-H300 text-black flex items-center justify-center ng-star-inserted").click();
        Thread.sleep(1000);
        checkout.clickBypass();
        checkout.clickCloseDrawer();
        checkout.sendEmail(TestData.EMAIL_ADDRESS);
        rb.keyPress(KeyEvent.VK_PAGE_DOWN);
        rb.keyRelease(KeyEvent.VK_PAGE_DOWN);

        checkout.printEmail();
        assoc.inputStore(TestData.ASSOCIATE);

       // old session after login-"h-full w-full ng-tns-c438376839-13 ng-star-inserted"
       //After New transaction without login- "h-full w-full ng-tns-c438376839-50 ng-star-inserted"
        //On click New Cart - "h-full w-full ng-tns-c438376839-12 ng-star-inserted"
       
    }

    @AfterTest
    public void teardown() {
        WindowsDriverManager.quitDriver();
        ScreenshotUtil.saveReport();
        System.out.println("Transaction1 test completed. Results will be processed by TestListener.");
    }
}

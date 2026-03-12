package Testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import Pages.Associate;
import Pages.Cart;
import Pages.Checkout;
import Pages.Customer;
import Pages.GetTransactionId;
import Pages.Login;
import Pages.ReturnPage;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import utils.ScreenshotUtil;
import utils.WindowsDriverManager;
import Data.TestData;

public class Exchanges {

    public WindowsDriver<WindowsElement> driver;
    private Login loginPage;
    private Associate assoc;
    private Customer cust;
    private Cart cart;
    private Checkout checkout;
    private GetTransactionId transactionPage;
    private ReturnPage returnPage;

//    public static String bearerToken;
    String username;
    String password;

    @Parameters({"username","password"})
    @BeforeGroups
    public void setup(String username, String password) {

        this.username = username;
        this.password = password;

        System.out.println("Running test with user: " + username);
    }

    @BeforeTest
    public void setup() throws Exception {
        driver = WindowsDriverManager.getDriver(TestData.APP_ID);
        driver.manage().window().maximize();
       
        loginPage = new Login(driver);
        assoc = new Associate(driver);
        cust = new Customer(driver);
        cart = new Cart(driver);
        checkout = new Checkout(driver);
        transactionPage = new GetTransactionId(driver);
        returnPage = new ReturnPage(driver);

    }

    @Test  (priority=0)
    public void EqualExchanges() throws InterruptedException, AWTException {
    	
    	  assoc.inputStore(TestData.ASSOCIATE);
          assoc.selectEmailOption();
          assoc.clickApply();

          cust.cancel();


        // Start Return
        returnPage.startReturn();

        // Enter Order Number
        cart.sendEnterOnInput( TestData.ORDER_NUMBER);

        // Enter POS Input
        cart.sendEnterOnInput(TestData.POS_INPUT2);
       
        // Cancel item via Robot click
        returnPage.selectdropdownUsingRobot();

        // Select reason & apply
        returnPage.selectReturnReasonAndApply();
        
        Thread.sleep(1000);
        returnPage.mainMenu();
        returnPage.selectCartMode();
        Thread.sleep(1000);

		 cart.sendEnterOnInput(TestData.POS_INPUT2);
        
        checkout.clickCheckout();

        // Cancel popup via Robot
     
        Thread.sleep(1000);
        checkout.sendEmail(TestData.EMAIL_ADDRESS);
        checkout.pageDown();
        checkout.printEmail();
      

            String eeorderNumber =
                    transactionPage.fetchTransactionOrder("010");
            TestData.EEORDER_NUMBER = eeorderNumber;

            Assert.assertNotNull(eeorderNumber, "Order number should not be null");
            Assert.assertTrue(eeorderNumber.contains("010"),
                    "Order number does not contain expected value");

            System.out.println("Verified Order Number: " + eeorderNumber);
    }
    
    @Test    (priority=1)
    public void PositiveExchanges() throws InterruptedException, AWTException {
    	
    	  assoc.inputStore(TestData.ASSOCIATE);
          assoc.selectEmailOption();
          assoc.clickApply();

          cust.cancel();


        // Start Return
        returnPage.startReturn();

        // Enter Order Number
        cart.sendEnterOnInput( TestData.ORDER_NUMBER);

        // Enter POS Input
        cart.sendEnterOnInput(TestData.POS_INPUT4);
        Thread.sleep(1000);
      
        // Cancel item via Robot click
        returnPage.selectdropdownUsingRobot();

        // Select reason & apply
        returnPage.selectReturnReasonAndApply();
        returnPage.mainMenu();
        returnPage.selectCartMode();
        Thread.sleep(1000);

		 cart.sendEnterOnInput(TestData.POS_INPUT3);
        
        
        checkout.clickCheckout();

        checkout.clickCash();
        checkout.enterAmountAndPay(TestData.AMOUNT);
        checkout.clickBypass();
        checkout.clickCloseDrawer();
        checkout.sendEmail(TestData.EMAIL_ADDRESS);
        checkout.pageDown();
        checkout.printEmail();
    }

    @Test    (priority=2)
    public void NegativeExchanges() throws InterruptedException, AWTException {
    assoc.inputStore(TestData.ASSOCIATE);
    assoc.selectEmailOption();
    assoc.clickApply();

    cust.cancel();


  // Start Return
  returnPage.startReturn();

  // Enter Order Number
  cart.sendEnterOnInput( TestData.ORDER_NUMBER);

  // Enter POS Input
  cart.sendEnterOnInput(TestData.POS_INPUT3);
  Thread.sleep(1000);
  // Cancel item via Robot click
  returnPage.selectdropdownUsingRobot();

  // Select reason & apply
  returnPage.selectReturnReasonAndApply();
  
  Thread.sleep(1000);
  returnPage.mainMenu();
  returnPage.selectCartMode();
  Thread.sleep(1000);

	 cart.sendEnterOnInput(TestData.POS_INPUT4);
  
	  checkout.clickCheckout();
      Thread.sleep(1000);

      // Cancel popup via Robot
      checkout.refund();

      

      checkout.clickBypass();
      checkout.clickCloseDrawer();
      Thread.sleep(1000);
      checkout.sendEmail(TestData.EMAIL_ADDRESS);
      checkout.pageDown();
      checkout.printEmail();
}
    
    
    
    
    
    @AfterTest
  public void teardown() {
        WindowsDriverManager.quitDriver();
        ScreenshotUtil.saveReport();
        System.out.println("Transaction1 test completed. Results will be processed by TestListener.");
    }
}

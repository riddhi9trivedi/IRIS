package Testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.awt.AWTException;

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

public class Return {

    public WindowsDriver<WindowsElement> driver;
    private Login loginPage;
    private Associate assoc;
    private Customer cust;
    private Cart cart;
    private Checkout checkout;
    private GetTransactionId transactionPage;
    private ReturnPage returnPage;
    String username;
    String password;

    @Parameters({"username","password"})
    @BeforeGroups
    public void setup(String username, String password) {

        this.username = username;
        this.password = password;

        System.out.println("Running test with user: " + username);
    }
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
        transactionPage = new GetTransactionId(driver);
        returnPage = new ReturnPage(driver);

    }

    @Test  
    public void verifyReturnFlow() throws InterruptedException, AWTException {
    	
    	  assoc.inputStore(TestData.ASSOCIATE);
          assoc.selectEmailOption();
          assoc.clickApply();

          cust.cancel();


        // Start Return
        returnPage.startReturn();

        // Enter Order Number
        cart.sendEnterOnInput( TestData.ORDER_NUMBER);

        // Enter POS Input
        cart.sendEnterOnInput(TestData.POS_INPUT1);
        Thread.sleep(1000);
        // Cancel item via Robot click
        returnPage.selectdropdownUsingRobot();

        // Select reason & apply
        returnPage.selectReturnReasonAndApply();
        
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
    
    public void verifyTransactionOrderNumber() throws InterruptedException {

        String RorderNumber =
                transactionPage.fetchTransactionOrder("010");
        TestData.RORDER_NUMBER = RorderNumber;

        Assert.assertNotNull(RorderNumber, "Order number should not be null");
        Assert.assertTrue(RorderNumber.contains("010"),
                "Order number does not contain expected value");

        System.out.println("Return Order Number: " + RorderNumber);
    }

    @AfterTest
  public void teardown() {
        WindowsDriverManager.quitDriver();
        ScreenshotUtil.saveReport();
        System.out.println("Transaction1 test completed. Results will be processed by TestListener.");
    }
}

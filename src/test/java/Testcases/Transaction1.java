package Testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.awt.AWTException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

public class Transaction1 {

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
        @BeforeTest
        public void setup(String username, String password) throws Exception {

            this.username = username;
            this.password = password;

            System.out.println("Running test with user: " + username);

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

public void validateitemdata() throws AWTException, InterruptedException {
	 loginPage.enterUsername(username);

     loginPage.enterPassword(password);

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
 	Thread.sleep(2000);
     WebElement SKU = driver.findElementByName(TestData.POS_INPUT1);
		String sku = SKU.getText().replace("\u00a0", "");
		Assert.assertEquals(sku, TestData.POS_INPUT1, "item is not available");
		System.out.println("SKU no: " + sku);
		Thread.sleep(2000);

		WebElement Associate = driver.findElementByName(TestData.EMAIL_OPTION);
		String associate = Associate.getText().replace("\u00a0", "");
		Assert.assertEquals(associate, TestData.EMAIL_OPTION, "associate is not available");
		System.out.println("Associate is present " + associate);
		Thread.sleep(2000);

		WebElement price = driver.findElementByName(TestData.ITEM_PRICE );
		String value = price.getText().replace("\u00a0", "");
		Assert.assertEquals(value, TestData.ITEM_PRICE, "price of item is not same");
		System.out.println("Price of an Item: " + value);
		Thread.sleep(2000);
     
   
     cart.Transaction_Summary();
     WebElement taxbeforecheckout = driver.findElementByName(TestData.Taxbeforecheckout);
		String value2 = taxbeforecheckout.getText().replace("\u00a0", "");
		Assert.assertEquals(value2, TestData.Taxbeforecheckout, "Calculated at checkout");
		System.out.println("Tax calculated on Cart: " + value2);
     
		Thread.sleep(2000);

     checkout.clickCheckout();
     cart.Transaction_Summary();
     WebElement tax  = driver.findElementByName(TestData.Taxvalue);
   		String appliedtax = tax.getText().replace("\u00a0", "");
   		Assert.assertEquals(appliedtax, TestData.Taxvalue, "$1.95");
   		System.out.println("Tax calculated on item: " + appliedtax);
     
     checkout.clickCash();
     checkout.enterAmountAndPay(TestData.AMOUNT);
     checkout.clickBypass();
     checkout.clickCloseDrawer();
     checkout.sendEmail(TestData.EMAIL_ADDRESS);
     checkout.pageDown();
     checkout.printEmail();
     
	
}
    @Test (
    	  
    	    dependsOnMethods = "validateitemdata"
    	)
    public void loginAndCheckoutFlow() throws Exception {


        assoc.inputStore(TestData.ASSOCIATE);
        assoc.selectEmailOption();
        assoc.clickApply();

        cust.cancel();

        cart.sendEnterOnInput(TestData.POS_INPUT1);
        cart.sendEnterOnInput(TestData.POS_INPUT2);
        cart.sendEnterOnInput(TestData.POS_INPUT3);
        cart.sendEnterOnInput(TestData.POS_INPUT4);
  
       
        checkout.clickCheckout();
       
        
        checkout.clickCash();
        checkout.enterAmountAndPay(TestData.AMOUNT);
        checkout.clickBypass();
        checkout.clickCloseDrawer();
        checkout.sendEmail(TestData.EMAIL_ADDRESS);
        checkout.pageDown();
        checkout.printEmail();
        
    }
    
    @Test (
    	  
    	    dependsOnMethods = "loginAndCheckoutFlow"
    	)
    public void verifyTransactionOrderNumber() throws InterruptedException {

        String orderNumber =
                transactionPage.fetchTransactionOrder("010");
        TestData.ORDER_NUMBER = orderNumber;

        Assert.assertNotNull(orderNumber, "Order number should not be null");
        Assert.assertTrue(orderNumber.contains("010"),
                "Order number does not contain expected value");

        System.out.println("Verified Order Number: " + orderNumber);
    }
    
  

    @AfterTest
    public void teardown() {
        WindowsDriverManager.quitDriver();
        ScreenshotUtil.saveReport();
        System.out.println("Transaction1 test completed. Results will be processed by TestListener.");
    }
}

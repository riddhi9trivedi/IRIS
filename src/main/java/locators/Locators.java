package locators;

public class Locators {
    // LoginPage
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String LOGIN_BUTTON = "kc-login";
    public static final String OK_BUTTON = "OK";
    public static final String HOME_NAV = "storeCartNextGen-ui-pos-home-l3";
  

    // HomePage
    public static final String INPUT = "input";
    public static final String EMAIL_OPTION_NAME = "100443@corp.gdglobal.ca";
    public static final String APPLY_BUTTON = "Apply";
    public static final String Summary = "header";
    public static final String CHECKOUT = "//Button[@Name=\"Checkout\"]";
    public static final String CASH = "Cash";

    // CheckoutPage
    public static final String PAY_BUTTON = "Pay";
    public static final String BYPASS = "Bypass";
    public static final String CLOSE_DRAWER = "Close Drawer";
    public static final String PRINT_AND_EMAIL = "Print & Email";
    
    // Menu / Buttons
    public static final String TRANSACTIONS_MENU = "TRANSACTIONS";
    public static final String ORDER_NUMBER_MENU = "Order Number";

    // XPath (partial match – dynamic order number)
    public static final String ORDER_NUMBER_XPATH =
            "//*[contains(@Name,'010')]";  // e.g. 010
    
    // Buttons / Menus
    public static final String START_RETURN = "Start A Return";
    public static final String TRANSACTIONS = "TRANSACTIONS";
    public static final String Cart_Mode = "Cart Mode";


    // Return Reason & Action
    public static final String COLOR_ISSUE = "Color issue";
    public static final String APPLY_BUTTONRT = "Apply";
    public static final String Reason = "//Group[@ClassName=\"select__combobox\"]";
    
    //Refund
    public static final String CONFIRM_BUTTON_CLASS =
            "overflow-unset text-H300 text-black flex items-center justify-center ng-star-inserted";
    
}
//Menu option AutomationId:	"storeBackOffice-ui-pos-hamburger-l2"

package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import io.appium.java_client.windows.WindowsDriver;


public class CommonUtil {

	public WindowsDriver driver;
	   private Properties prop;
	  

	    public CommonUtil() {
	        InputStream inputStream = null;
	        try {
	            prop = new Properties();
	          //  inputStream = ClassLoader.class.getResourceAsStream("ApplicationConfig.properties");
	           
	            inputStream= new FileInputStream(new File("C:\\Users\\rtrivedi\\eclipse-workspace\\IRIS\\src\\main\\resources\\ApplicationConfig.properties"));
	            
	            prop.load(inputStream);
	        } catch (
	                Exception e) {
	            e.printStackTrace();
	        }

	    }

	    public String getPropertyFileValue(String key) {
	        return prop.getProperty(key);
	    }
	    
	    
	}


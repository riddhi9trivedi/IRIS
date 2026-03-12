
package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

import java.io.File;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[TEST START] " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[TEST PASS] " + result.getName());
        takeScreenshotAllure(result, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[TEST FAIL] " + result.getName());
        takeScreenshotAllure(result, "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[TEST SKIPPED] " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("[SUITE START] " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("[SUITE FINISH] " + context.getName());

        try {
            // 1️⃣ Correct Allure folder
            String sourceFolder = "allure-results";
            File folder = new File(sourceFolder);

            if (!folder.exists() || !folder.isDirectory()) {
                System.out.println("Allure results folder NOT found: " + sourceFolder);
                System.out.println("Skipping email and zip creation.");
                return;
            }

            // 2️⃣ Zip file output
            String zipFilePath = "allure-report.zip";
            AllureUtil.zipAllureResults(sourceFolder, zipFilePath);

            // 3️⃣ Email setup (Gmail)
            String host = "smtp.office365.com";            
            String port = "587";
            String user = "riddhit611@gmail.com";  //riddhit611@gmail.com";  
            String pass = "dfnpwdothuqqdvhv";            
            String to = "rtrivedi@dynamite.ca";           

            String subject = "Automated Test Report - " + context.getName();
            String body = "Hi,<br><br>Please find the attached Allure test report.<br><br>Regards,<br>Automation Team";

            // 4️⃣ Send email with attachment
            AllureUtil.sendEmailWithAttachment(host, port, user, pass, to, subject, body, zipFilePath);

            System.out.println("Allure Report Email Sent Successfully!");

        } catch (Exception e) {
            System.out.println("Failed to zip or email report:");
            e.printStackTrace();
        }
    }

    /** Helper to take screenshot and attach to Allure */
    private void takeScreenshotAllure(ITestResult result, String status) {
        try {
            Object testObj = result.getInstance();
            WindowsDriver<WindowsElement> driver =
                    (WindowsDriver<WindowsElement>) testObj.getClass().getDeclaredField("driver").get(testObj);

            // Save screenshot with existing ScreenshotUtil
            File screenshot = ScreenshotUtil.takeScreenshot(driver, result.getName() + "_" + status);

            // Attach to Allure
            if (screenshot != null) {
                try (java.io.FileInputStream fis = new java.io.FileInputStream(screenshot)) {
                    io.qameta.allure.Allure.addAttachment(result.getName() + "_" + status, fis);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot for Allure:");
            e.printStackTrace();
        }
    }
}


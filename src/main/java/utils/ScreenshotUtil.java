package utils;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {

    private static StringBuilder reportBuilder = new StringBuilder();

    // Initialize HTML report
    static {
        reportBuilder.append("<html><head><title>Automation Report</title></head><body>");
        reportBuilder.append("<h1>Execution Report</h1>");
    }

    /**
     * Takes a screenshot, saves it to /test-output/screenshots/,
     * adds it to HTML report AND attaches to Allure.
     */
    public static File takeScreenshot(WindowsDriver<WindowsElement> driver, String stepName) {
        try {
            // Create screenshot with Appium
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Create output directory
            File destDir = new File("test-output/screenshots/");
            if (!destDir.exists()) destDir.mkdirs();

            // Clean filename (no spaces, no special chars)
            String safeStep = stepName.replaceAll("[^a-zA-Z0-9_-]", "_");

            String filename = safeStep + "_" + System.currentTimeMillis() + ".png";
            File dest = new File(destDir, filename);

            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            /** 🔥 1. Add screenshot to your HTML report */
            reportBuilder.append("<h3>").append(stepName).append("</h3>");
            reportBuilder.append("<img src='screenshots/")
                    .append(filename)
                    .append("' width='600'><br><br>");

            /** 🔥 2. Attach screenshot to Allure with correct MIME type */
            try (FileInputStream fis = new FileInputStream(dest)) {
                Allure.addAttachment(stepName, "image/png", fis, "png");
            }

            return dest;

        } catch (IOException e) {
            e.printStackTrace();
            Allure.addAttachment("Screenshot Error", e.toString());
            return null;
        }
    }

    /** Save HTML execution report */
    public static void saveReport() {
        try {
            reportBuilder.append("</body></html>");

            File reportFile = new File("test-output/ExecutionReport.html");
            File folder = new File("test-output");
            if (!folder.exists()) folder.mkdirs();

            Files.write(reportFile.toPath(), reportBuilder.toString().getBytes());

            System.out.println("ExecutionReport.html saved successfully");

        } catch (Exception e) {
            e.printStackTrace();
            Allure.addAttachment("HTML Report Error", e.toString());
        }
    }
}

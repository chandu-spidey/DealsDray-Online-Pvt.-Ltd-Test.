package supriya_test.wesite_test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class supriya_test001 {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		int[][] desktopResolutions = {
                {1920, 1080},
                {1366, 768},
                {1536, 864}
        };

        // Mobile resolutions
        int[][] mobileResolutions = {
                {360, 640},
                {414, 896},
                {375, 667}
        };


        
        for (int[] resolution : desktopResolutions) {
            captureScreenshot(driver, "https://www.getcalley.com/page-sitemap.xml", resolution[0], resolution[1], "Desktop_" + resolution[0] + "x" + resolution[1]);
        }

        
        driver.manage().window().setSize(new Dimension(414, 736)); // Set a mobile-like size for Chrome

        
        for (int[] resolution : mobileResolutions) {
            captureScreenshot(driver, "https://www.getcalley.com/page-sitemap.xml", resolution[0], resolution[1], "Mobile_" + resolution[0] + "x" + resolution[1]);
        }

        // Quit WebDriver
        driver.quit();
    }

    public static void captureScreenshot(WebDriver driver, String url, int width, int height, String fileName) {
        
        driver.manage().window().setSize(new Dimension(width, height));
        driver.get(url);

        try {
            // Capture screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("screenshots/" + fileName + getCurrentDateTime() + ".png");
            FileUtils.copyFile(screenshot, destFile);
            System.out.println("Screenshot captured: " + destFile.getAbsolutePath());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public static String getCurrentDateTime() {
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        return dateFormat.format(new Date());
		
	}
    
	}


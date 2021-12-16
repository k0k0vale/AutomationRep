package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

import tests.TestPageObject;


public class Utility {
	
	private static final Logger log = LogManager.getLogger(TestPageObject.class.getName());
	static final String jarPath = "c:\\jars\\drivers\\";
	
	// Get the web driver by browser type and get URL
		public static WebDriver getWebDriver(String sBrowser, String sUrl) throws IOException {

		
			WebDriver driver;
			log.info("Browser: " + sBrowser + ", Start page: " + sUrl);

			switch (sBrowser.toLowerCase()) {
			case "firefox":
				System.setProperty("webdriver.firefox.marionette", jarPath + "geckodriver.exe");
				driver = new FirefoxDriver();
				break;

			case "chrome":
				System.setProperty("webdriver.chrome.driver", jarPath + "chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("useAutomationExtension", false);

				driver = new ChromeDriver(options);
				break;

			case "ie":
				File file = new File(jarPath + "IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver();
				break;

			default:
				System.setProperty("webdriver.firefox.marionette", jarPath + "\\geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			}

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.get(sUrl);
			driver.manage().window().maximize();

			return driver;

		}

		
		public static String getProperty(String key) throws IOException {
			Properties prop = new Properties();
			
			String datafile = "datafile.test.properties";
		
			InputStream input = null;
			input = new FileInputStream((System.getProperty("user.dir")) + "\\" + datafile);
	
			prop.load(input);
			return prop.getProperty(key);

		}
		
		
		// method which take print screen
		public static File printScreen(WebDriver driver) throws IOException
		{
		
			//Take the image of WebDriver
			File snap = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			//method which bring timestamp string
			DateFormat timestamp = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			String strTimeStamp = timestamp.format(new Date());
			
			//Set String as path of the file
			String imgPath = "C:\\images\\" + strTimeStamp +"_image.png";
			
			//Create empty file
			File fImage = new File(imgPath);
			
			//copy image into our empty file
			FileUtils.copyFile(snap, fImage);
			
			//return image
			return fImage;
			
		}
		
		public static void addSnapShotToReport(File file) {
			Reporter.log("<br/><p><img width='612' src='" + file.getAbsolutePath() + "'></p>");
		}

		

}

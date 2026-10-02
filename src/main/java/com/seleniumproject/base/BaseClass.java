package com.seleniumproject.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.seleniumproject.actiondriver.ActionDriver;

public class BaseClass {
	protected static Properties prop;
	protected static WebDriver driver;
	private static ActionDriver actionDriver;
	

	@BeforeSuite
	// To load the Configuration file from the Resource config.properties
	public void LoadConfig() throws IOException {
		// Load the configuration file
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/congifg.properties");
		prop.load(fis);

	}

	@BeforeMethod
	public void setup() throws IOException {
		System.out.println("setting up webdriver for:" + this.getClass().getSimpleName());
		launchBrowser();
		configBrowser();
		staticwait(2);

		// Initiallize ActionDriver only once
		if (actionDriver == null) {
			actionDriver = new ActionDriver(driver);
			System.out.println("actionDriver instance is created ");
		}

	}

	// Initialize the WebDriver based on browser defined in config.properties file
	private void launchBrowser() {

		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();

		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Browser Not Supported:+browser");
		}

	}

	// Configure browser setting like implicit wait ,maximize the browser and
	// navigate to URL

	private void configBrowser() {
		// Implicit Wait
		int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		// maximize the driver
		driver.manage().window().maximize();

		// Navigate to URL
		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
			System.out.println("Failed to navigate to the url:" + e.getMessage());

		}

	}

	@AfterMethod
	public void tearDown() {
		staticwait (10);
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("driver failed to quit:" + e.getMessage());
			}
		}
		System.out.println("Webdriver instance is closed");
		driver =null;
		actionDriver =null;
	}


	/*
	 * // Driver getter method to get the driver outside of package 
	 * public WebDriver
	 * getDriver() { return driver; }
	 */

	// Getter Method for WebDriver
	public static WebDriver getDriver() {

		if (driver == null) {
			System.out.println("webDriver is not initialized");
			throw new IllegalStateException("webDriver is not initialized");
		}
		return driver;

	}

	// Getter Method for ActionDriver
	public static ActionDriver getActionDriver() {

		if (actionDriver == null) {
			System.out.println("ActionDriver is not initialized");
			throw new IllegalStateException("ActionDriver is not initialized");
		}
		return actionDriver;

	}
	
	 //Get method for PROP
	 public static Properties getprop() {
			return prop;
		}

	// Driver setter method
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * //properties setter method public Properties setprop () { return prop; }
	 */

	// Static wait for pause

	public void staticwait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
	
	public void scrollByPixels(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }
	
}

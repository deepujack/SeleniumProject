package com.seleniumproject.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.seleniumproject.base.BaseClass;

public class ActionDriver {

     private WebDriver driver;
     private WebDriverWait wait;
     
     public ActionDriver (WebDriver driver) {
    		 this.driver = driver;
    		 int explicitWait = Integer.parseInt(BaseClass.getprop().getProperty("explicitWait"));
    			this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
    		}
     
     //Method to click an Element
     
     public void click (By by) {
    	 try {
    		 waitforElementToBeClickable(by);
    		 driver.findElement(by).click();
    	 } catch (Exception e) {
    		 System.out.println("Element is not clickable:"+ e.getMessage());
    	 }
    	 
     }
     

 	// Method to enter the text into a input field -->Avoid the code duplication -->
 	// fix the multiple call 
     
    	 public void enterText(By by, String value) {
    			try {
    				waitforElementToBeVisible(by);
    				WebElement element = driver.findElement(by);
    				element.clear();
    				element.sendKeys(value);
    			} catch (Exception e) {
    				System.out.println("Unable to enter the value:" + e.getMessage());
    			}
    		}
 
     
    	// Method to get text from an input field

    		public String getText(By by) {
    			try {
    				waitforElementToBeVisible(by);
    				return driver.findElement(by).getText();
    			} catch (Exception e) {
    				System.out.println("Unable to get the Text:" + e.getMessage());
    				return "";
    			}

    		}
    		
    		
    		// Method to compare both text
    		public boolean compareText(By by, String expectedText) {
    			try {
    				waitforElementToBeVisible(by);
    				String Actualtext = driver.findElement(by).getText();
    				if (expectedText.equals(Actualtext)) {
    					System.out.println("Text are matching:" + Actualtext + "equals" + expectedText);
    					return true;

    				} else {
    					System.out.println("Text are not matching:" + Actualtext + "Not equals" + expectedText);
    					return false;
    				}
    			} catch (Exception e) {

    				System.out.println("Cannot compare the text:" + e.getMessage());
    			}
    			return false;

    		}
    		
    		
    		// Simplified the method & removed the redundant conditions
    		public boolean isDisplayed(By by) {
    			try {
    				waitforElementToBeVisible(by);
    				return driver.findElement(by).isDisplayed();
    			} catch (Exception e) {
    				System.out.println("element is not displayed:" + e.getMessage());
    				return false;
    			}

    		}
    		
    		
    		// Wait for the page load

    		public void waitForPageLoad(int timeOutInSec) {
    			try {
    				wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until((WebDriver driver) -> ((JavascriptExecutor) driver)
    						.executeScript("return document.readyState").equals("complete"));
    				System.out.println("Page loaded successfully.");
    			} catch (Exception e) {
    				System.out.println("Page did not load within " + timeOutInSec + " seconds. Exception: " + e.getMessage());
    			}
    		}

    		// scroll to an element
    		public void scrollToElement(By by) {
    			try {
    				JavascriptExecutor js = (JavascriptExecutor) driver;
    				WebElement element = driver.findElement(by);
    				js.executeScript("arguments[0],scrollIntoView(true);", element);
    			} catch (Exception e) {
    				System.out.println("Unable to Scroll to element:" + e.getMessage());
    			}
    		}

    		
     
     
     // Wait for element to be clickable
     
	private void waitforElementToBeClickable(By by) {
		// TODO Auto-generated method stub
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {

			System.out.println("Element is not clickable :" + e.getMessage());
		}
	}
	
	
	// Wait for element to be visible
		private void waitforElementToBeVisible(By by) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			} catch (Exception e) {
				System.out.println("Element is not Visible:" + e.getMessage());
			}
		}

	}



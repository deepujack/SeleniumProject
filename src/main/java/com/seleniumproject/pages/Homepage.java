package com.seleniumproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.seleniumproject.actiondriver.ActionDriver;
import com.seleniumproject.base.BaseClass;

public class Homepage {

	private ActionDriver actiondriver ;
	
	// Define locators by using class 
	private By mainpage = By.xpath("//a[normalize-space()='Home']");
	private By signup = By.xpath("//a[normalize-space()='Signup / Login']\r\n"
			+ "");
	
	// Initialize the ActionDriver object by passing WebDriver instance
	
	public Homepage (WebDriver driver ) {
		this.actiondriver = BaseClass.getActionDriver();
	}
	
	
	//Verify whether the mainpage/home page is visible 
	
	

	public boolean IsHomeTabisVisible () {
		return actiondriver.isDisplayed(mainpage);
	}

	public void loginPage ( ) {
	 actiondriver.click(signup);
	}
}

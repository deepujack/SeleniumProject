package com.seleniumproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.seleniumproject.actiondriver.ActionDriver;
import com.seleniumproject.base.BaseClass;

public class LoginPage {

private ActionDriver actiondriver ;


//define locators using class/Xpath 
private By NewUserField =By.xpath("//input[@placeholder='Name']");
private By EmailId = By.cssSelector(" input[data-qa='signup-email']");
private By SignUp = By.xpath(" (//button[normalize-space()='Signup'])[1]");




public LoginPage (WebDriver driver) {
this.actiondriver = BaseClass.getActionDriver();
	}


public void login (String Name, String Email) { 
	actiondriver.enterText(NewUserField, Name);
	actiondriver.enterText(EmailId, Email);
	actiondriver.click(SignUp);
	
}


}

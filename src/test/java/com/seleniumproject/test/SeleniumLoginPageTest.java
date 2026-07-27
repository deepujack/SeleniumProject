package com.seleniumproject.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.seleniumproject.base.BaseClass;
import com.seleniumproject.pages.Homepage;
import com.seleniumproject.pages.LoginPage;

public class SeleniumLoginPageTest extends BaseClass {
	
	
	private LoginPage loginPage;
	private Homepage homepage;
	
	
	@BeforeMethod
	public void setupPages() {
		loginPage = new LoginPage(getDriver());
		homepage = new Homepage(getDriver());
	}
	
	@Test
	public void verifyValidLoginTest () {
		loginPage.login("Deepak", "Deepugowda293@gmail.com");
		Assert.assertTrue(homepage.IsHomeTabisVisible(), "Admin tab is visible after successful login ");
		
	}
	

	
	}



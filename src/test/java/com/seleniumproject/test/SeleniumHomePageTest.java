package com.seleniumproject.test;

import com.seleniumproject.base.BaseClass;
import com.seleniumproject.pages.Homepage;
import com.seleniumproject.pages.LoginPage;

public class SeleniumHomePageTest extends BaseClass {
	
	
	private LoginPage loginpage;
	private Homepage homepage;
	
	
	public void setupPages () {
		loginpage = new LoginPage(getDriver());
		homepage = new Homepage (getDriver());
	}
	

	
}

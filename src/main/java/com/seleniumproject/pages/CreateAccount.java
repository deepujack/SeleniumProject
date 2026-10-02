package com.seleniumproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.seleniumproject.actiondriver.ActionDriver;
import com.seleniumproject.base.BaseClass;

public class CreateAccount {
	// TODO Auto-generated method stub
        private ActionDriver actiondriver;
        private WebDriver driver;
        
        //Define locators using class/Xpath 
        
        private By NewTitle = By.xpath("//*[@id='uniform-id_gender1']");
        private By CreatePassword = By.xpath("//*[@id='password']");
        private By SelectDateOfBirthDay = By.xpath("//*[@id=\"days\"]");
            

            public CreateAccount (WebDriver driver) {
            	this.driver = driver;
            	this.actiondriver = BaseClass.getActionDriver();
            	
            }
          
            public void scrollDown() {
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
            }
              public void Selecttile () {
            	  actiondriver.click(NewTitle);
              }
              
              public void CreatePassword () {
            	  actiondriver.enterText(CreatePassword,"123456");
   
              }
              
              public void SelectDateOfBirth () {
//            	  actiondriver.scrollToElement(SelectDateOfBirthDay);
            	  actiondriver.click(SelectDateOfBirthDay);
            	
              }
       




}

    

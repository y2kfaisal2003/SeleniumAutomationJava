package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import com.baseClass.BasePage;

public class GooglePage extends BasePage{   
	public GooglePage() {
		super(driver);
	}

	// Web Elements
	@FindBy(xpath ="//input[@name='q']")
	@CacheLookup
	WebElement googleSearchTextBox;

	@FindBy(xpath ="//img[@id='hplogo']")
	@CacheLookup
	WebElement googleLogo;

	@FindBy(xpath="//input[@value='Google Search']")
	@CacheLookup
	WebElement googleSearchButton;

	// Web Elements Page Actions
	public void EnterGoogleSearchText(String strSearchValue){
		googleSearchTextBox.sendKeys(strSearchValue);
	}

	public Boolean AT(String strPageTitle) throws Exception {
		String strTitle = driver.getTitle();
		try{
		if(strTitle.equalsIgnoreCase(strPageTitle))
			return true;
		else
			throw new Exception("Page Title expected value: "+strPageTitle+" is not matching with actual value: "+strTitle);
		}catch(Exception e)
		{
			//throw new Exception("Page Title expected value: "+strPageTitle+" is not matching with actual value: "+strTitle);
			throw new Exception(e.getMessage());
		}
	}
}

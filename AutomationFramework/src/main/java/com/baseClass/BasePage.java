package com.baseClass;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	protected static RemoteWebDriver driver;
	public BasePage(RemoteWebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);

    }
}

package com.test;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestClass {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Hunza\\selenium Jar files\\geckodriver-v0.16.1-win32\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/Hunza/KM/Selenium/Ali/Ali/Application/SeleniumExampleSite.html");
		Select selectObject = new Select(driver.findElement(By.id("cars")));
		selectObject.selectByVisibleText("Santro");
		selectObject.selectByValue("hyundai");
		driver.findElement(By.id("inp1")).sendKeys("50000");
		driver.findElement(By.id("inp2")).sendKeys("10");
		driver.findElement(By.id("inp3")).sendKeys("10");
		driver.findElement(By.tagName("button")).click();
		String strValue=driver.findElement(By.id("disp")).getText();
		String strValue2=selectObject.getFirstSelectedOption().getText();
		Assert.assertEquals("5000", strValue);
		Assert.assertEquals("Santro", strValue2);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());

	}

}

package com.framework;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.testConfiguration.TestEnvironmentConfiguration;

public class DriverFactory {

	private static RemoteWebDriver _driver;
	
	public static RemoteWebDriver getDriver() throws Exception{
		try{
			switch(TestEnvironmentConfiguration.getTestConfiguration("AutomationExecution").trim().toUpperCase()){
			case "LOCAL":
				_driver = getLocalDriver();
				return _driver;
			case "GRID":
				try{
				String url = TestEnvironmentConfiguration.getTestConfiguration("GridLink").trim();
				_driver = new RemoteWebDriver(new URL(url), null);
				return _driver;
				}catch(Exception e){
					throw new Exception("Grid URL: <B>"+TestEnvironmentConfiguration.getTestConfiguration("GridLink").trim()+"</B> is not reachable");
				}
			case "SAUCELAB":
				// Sauce Lab configuration needs to be decided 
				_driver = null;
				return _driver;
			default:
				throw new Exception("Automation Execution enviroment: <B>"+TestEnvironmentConfiguration.getTestConfiguration("AutomationExecution").trim()+"</B> is not correct");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
	}

	public static RemoteWebDriver getLocalDriver() throws IOException{
		try{
			switch(TestEnvironmentConfiguration.getTestConfiguration("Browser").trim().toUpperCase()){
			case "CHROME":
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/DriverExecutables/chromedriver.exe");
				_driver = new ChromeDriver(getChromeBrowserOptions());
				return _driver;
			case "FIREFOX":
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/DriverExecutables/geckodriver.exe");
				_driver = new FirefoxDriver(getFirefoxBrowserOptions());
				return _driver;
			case "IE":
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/DriverExecutables/IEDriverServer.exe");
				_driver = new InternetExplorerDriver(getIEBrowserOptions());
				return _driver;
			default:
				throw new Exception("Browser type: <B>"+TestEnvironmentConfiguration.getTestConfiguration("Browser").trim()+"</B> is not correct");
			}
		} catch (Exception e) {
			throw new IOException("Browser type: <B>"+TestEnvironmentConfiguration.getTestConfiguration("Browser").trim()+"</B> is not correct");
		}
	}

	public static ChromeOptions  getChromeBrowserOptions() throws Exception{
		try{
			if(TestEnvironmentConfiguration.getTestConfiguration("Browser").trim().toUpperCase().contains("CHROME")){
				ChromeOptions chromeOptions= new ChromeOptions();
				chromeOptions.setCapability(CapabilityType.BROWSER_NAME, "chrome");
				chromeOptions.addArguments("test-type");
				chromeOptions.addArguments("start-maximized");
				//chromeOptions.addArguments("--window-size=1920,1080");
				chromeOptions.addArguments("--enable-precise-memory-info");
				chromeOptions.addArguments("--disable-popup-blocking");
				chromeOptions.addArguments("--disable-default-apps");
				chromeOptions.addArguments("test-type=browser");
				return chromeOptions;
			}
			else
				throw new Exception("Exception Occuras while reading Chrome browser's options!");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static FirefoxOptions  getFirefoxBrowserOptions() throws Exception{
		try{
			if(TestEnvironmentConfiguration.getTestConfiguration("Browser").trim().toUpperCase().contains("FIREFOX")){
				FirefoxOptions firefoxOption = new FirefoxOptions();
				firefoxOption.addArguments("test-type");
				firefoxOption.addArguments("start-maximized");
				//firefoxOption.addArguments("--window-size=1920,1080");
				firefoxOption.addArguments("--enable-precise-memory-info");
				firefoxOption.addArguments("--disable-popup-blocking");
				firefoxOption.addArguments("--disable-default-apps");
				firefoxOption.addArguments("test-type=browser");
				return firefoxOption;
			}else
				throw new Exception("Exception Occuras while reading FireFox browser's options!");

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static InternetExplorerOptions  getIEBrowserOptions() throws Exception{
		try{
			if(TestEnvironmentConfiguration.getTestConfiguration("Browser").trim().toUpperCase().contains("IE")){
				InternetExplorerOptions IEOption = new InternetExplorerOptions();
				//IEOption.addArguments("start-maximized");
				// IE Options implementation is pending
				return IEOption;
			}
			else
				throw new Exception("Exception Occuras while reading Internet Explorer browser's options!");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	// Trying to minimize to set of code but MutableCapabilities is deprecated in 
	public static MutableCapabilities  getBrowserOptions() throws Exception{
		String strBrower =TestEnvironmentConfiguration.getTestConfiguration("Browser").trim().toUpperCase();
		try{
			if(strBrower.contains("CHROME")){
				ChromeOptions chromeOptions= new ChromeOptions();
				chromeOptions.setCapability(CapabilityType.BROWSER_NAME, "chrome");
				chromeOptions.addArguments("test-type");
				chromeOptions.addArguments("start-maximized");
				//chromeOptions.addArguments("--window-size=1920,1080");
				chromeOptions.addArguments("--enable-precise-memory-info");
				chromeOptions.addArguments("--disable-popup-blocking");
				chromeOptions.addArguments("--disable-default-apps");
				chromeOptions.addArguments("test-type=browser");
				return chromeOptions;
			}else if(strBrower.contains("FIREFOX"))
			{
				FirefoxOptions firefoxOption = new FirefoxOptions();
				firefoxOption.addArguments("test-type");
				firefoxOption.addArguments("start-maximized");
				//firefoxOption.addArguments("--window-size=1920,1080");
				firefoxOption.addArguments("--enable-precise-memory-info");
				firefoxOption.addArguments("--disable-popup-blocking");
				firefoxOption.addArguments("--disable-default-apps");
				firefoxOption.addArguments("test-type=browser");
				return firefoxOption;
			}else if(strBrower.contains("IE")){
				InternetExplorerOptions IEOption = new InternetExplorerOptions();
				//IEOption.addArguments("start-maximized");
				// IE Options implementation is pending
				return IEOption;
			}
			else
				throw new Exception("Exception Occuras while reading "+strBrower+" options!");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}

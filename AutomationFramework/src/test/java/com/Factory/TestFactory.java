package com.Factory;

import java.io.IOException;
import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.TestRunner;

import com.baseClass.BasePage;
import com.framework.DriverFactory;
import com.framework.TestReporter;
import com.testConfiguration.TestEnvironmentConfiguration;

public class TestFactory {

	public ThreadLocal<RemoteWebDriver> tlRemoteWebDriver = new ThreadLocal<RemoteWebDriver>();
	//private static ThreadLocal<TestReporter> tlTestReporter = new ThreadLocal<TestReporter>();
	protected RemoteWebDriver webDriver =tlRemoteWebDriver.get();
	protected TestReporter testRepoter = new TestReporter();//= tlTestReporter.get();
	//protected TestReporter testRepoter = tlTestReporter.get();

	
	@BeforeSuite
	public void beforeTestSuite() throws Exception
	{
		testRepoter.createExtentRepoter();
	}
	
	@BeforeClass
	public void startTest() throws Exception
	{
		testRepoter.createTestClass(getClass().getSimpleName());
		createDriver();//webDriver=DriverFactory.getDriver();
		launchApplication();
		new BasePage(webDriver);
	}
	
	@BeforeMethod
	public void beforeTestMethod(Method method) throws IOException{
		try{
		testRepoter.beforeMethodTestReporter(method.getName());
		}catch(Exception e){
			testRepoter.Fail(e.getMessage());
		}
	}

	@AfterMethod
	public void afterTestMethod(ITestResult result) throws IOException{
		try{
		testRepoter.afterMethodTestReporter(result);
		}catch(Exception e){
			testRepoter.Fail(e.getMessage());
		}
	}
	
	@AfterClass
	public void tearDown() throws IOException{
		//tlBasePage.get();
		webDriver.quit();
		tlRemoteWebDriver.remove();
		testRepoter.flushExtentRepoter();
	}
	
	@AfterSuite
	public void AfterTestSuite() throws Exception
	{
		testRepoter.flushExtentRepoter();
	}
	
//	@AfterTest
//	public void endTest() throws Exception
//	{
//		webDriver.quit();
//		tlRemoteWebDriver.remove();
//		testRepoter.flushExtentRepoter();
//	}

	
//	@BeforeClass
//	public void setUp(ITestContext testContext) throws Exception{
//		try {
//			testRepoter.createTestClass(getClass().getSimpleName());
//			//crateDevice();
//			createDriver();
//			launchApplication();
//			//createTest();
//			//createBasePage();
//		} catch (Exception e) {
//			testRepoter.Fail(e.getMessage());
//			//throw new Exception(e.getMessage());
//		}
//	}

	public TestReporter Repoter(){
		return testRepoter;
	}

	private void launchApplication() throws IOException {
		try {
			webDriver.get(getApplicationUrl());
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
	}

	private void createDriver() throws Exception {
		try {
			webDriver = DriverFactory.getDriver();
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}

	}

	private static String getApplicationUrl() throws IOException{
		try{
			String strEnvironment =TestEnvironmentConfiguration.getTestConfiguration("TestEnvironment").trim();
			return TestEnvironmentConfiguration.getTestConfiguration("AUTLink"+strEnvironment);
		}catch(Exception e){
			throw new IOException("Failed to read the environment link for : "+TestEnvironmentConfiguration.getTestConfiguration("TestEnvironment").trim());
		}
	}
}

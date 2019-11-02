package com.framework;

import java.io.IOException;
//import java.lang.reflect.Method;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.testConfiguration.TestEnvironmentConfiguration;

public class TestReporter {
	private static ExtentReports extent;
	//private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static ExtentTest parentTest;
	private static ExtentTest test;

	// @BeforeSuite
	public void createExtentRepoter() throws Exception {
		try {
			extent = ExtentManager.createInstance(getExtentReportFile());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		//		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		//		extent.attachReporter(htmlReporter);
	}

	//@BeforeClass
	public synchronized void createTestClass(String className) {
		parentTest  = extent.createTest(className);
	}

	//@BeforeMethod
	//public synchronized void beforeMethodTestReporter(Method method) {
	public synchronized void beforeMethodTestReporter(String methodName) {
		//ExtentTest child = ((ExtentTest) parentTest.get()).createNode(method.getName());
		test  = parentTest.createNode(methodName);
	}

	// @AfterSuite
	public void flushExtentRepoter()  {
		try {
			extent.flush();
		} catch (Exception e) {
			try {
				throw new Exception(e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void Log(String logDetails){
		test.log(Status.INFO, logDetails);
	}

	public void Info(String logDetails){
		test.info(logDetails);
	}

	public void Pass(String logDetails){
		test.pass(logDetails);
	}

	public void FinalPass(String logDetails) {
		try {
			test.pass(logDetails,MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Fail(String logDetails) throws IOException{
		if(test!=null)
			test.fail(logDetails, MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
		else
			parentTest.fail(logDetails);
	}

	//@AfterMethod
	public synchronized void afterMethodTestReporter(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE)
			test.fail(result.getThrowable());
		else if (result.getStatus() == ITestResult.SKIP)
			test.skip(result.getThrowable());
		else
			test.pass("Test passed");
		extent.flush();
	}

	private String getExtentReportFile() {
		try{
			if(TestEnvironmentConfiguration.getTestConfiguration("ReportFolderType").toUpperCase().trim().equalsIgnoreCase("LOCALFOLDER"))
				return getReportFileInLocalFolder();
			else if (TestEnvironmentConfiguration.getTestConfiguration("ReportFolderType").toUpperCase().trim().equalsIgnoreCase("SHAREDFOLDER"))
				return getReportFileInSharedFolder();
			else
				throw new Exception(TestEnvironmentConfiguration.getTestConfiguration("ReportFolderType")+" is not a valid Report folder type in Environment Configuration file");
		}catch(Exception e){

		}
		return null;
	}

	private String getReportFileInLocalFolder() throws Exception {
		StringBuilder fileDetails= new StringBuilder();
		String strFolderPath=System.getProperty("user.dir")+"/TestReports";
		createReportDirectory(strFolderPath);
		try {
			return fileDetails.append(strFolderPath)
					.append("/")
					.append(TestEnvironmentConfiguration.getTestConfiguration("TestSuiteName").trim())
					.append("_")
					.append(getFileSuffix())
					.append(".html")
					.toString();
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private String getReportFileInSharedFolder() throws Exception {
		StringBuilder fileDetails= new StringBuilder();
		if(!TestEnvironmentConfiguration.getTestConfiguration("ReportFolderPath").trim().isEmpty()){
			String strFolderPath=TestEnvironmentConfiguration.getTestConfiguration("ReportFolderPath").trim();
			createReportDirectory(strFolderPath);
			try {
				return fileDetails.append(strFolderPath)
						.append("/")
						.append(TestEnvironmentConfiguration.getTestConfiguration("TestSuiteName").trim())
						.append(getFileSuffix())
						.append(".html")
						.toString();
			} catch (IOException e) {
				throw new IOException(e.getMessage());
			}
			catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}else
			throw new Exception("ReportFolderPath in Environment Configuration is empty");
	}

	private String getFileSuffix(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	private boolean createReportDirectory(String strPath) {
		boolean folderStatus=false;
		try{
			Path path =Paths.get(strPath);
			if(!Files.exists(path)){
				Files.createDirectory(path);
				folderStatus=true;
			}
		}
		catch(SecurityException ex)
		{
			throw new SecurityException("Failed to create folder in "+strPath+" due to security reason");
		}
		catch(Exception e)
		{
			throw new SecurityException("Failed to create folder in "+strPath);
		}
		return folderStatus;
	}
}

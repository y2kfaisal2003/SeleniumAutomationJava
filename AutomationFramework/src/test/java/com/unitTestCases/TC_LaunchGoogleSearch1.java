package com.unitTestCases;

import junit.framework.Assert;

import org.testng.annotations.Test;

import com.DataFolder.DataProviderSource;
import com.DataFolder.TestEntity;
import com.Factory.TestFactory;
import com.pageObjects.GooglePage;

public class TC_LaunchGoogleSearch1 extends TestFactory{ 

	@Test(dataProvider="TestMethod", dataProviderClass=DataProviderSource.class)
	public void TestMethodOne(TestEntity testData) throws Exception{
		
		GooglePage googlePage = new GooglePage();
		Repoter().Info("Open the Google Page");
		
		Assert.assertTrue(googlePage.AT(testData.getTitleValue()));
		Repoter().FinalPass("At Google Page");
		
		googlePage.EnterGoogleSearchText(testData.getSearchValue());
		Repoter().FinalPass("Search for Seleniumhq site");
	}
}

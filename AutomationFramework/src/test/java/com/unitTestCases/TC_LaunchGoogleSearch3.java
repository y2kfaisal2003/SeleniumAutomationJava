package com.unitTestCases;

import org.testng.annotations.Test;

import com.Factory.TestFactory;
import com.pageObjects.GooglePage;

public class TC_LaunchGoogleSearch3 extends TestFactory{ 

	@Test
	public void TestMethodThree(){
		
		GooglePage googlePage = new GooglePage();
		Repoter().Info("Open the Google Page");
		
		googlePage.EnterGoogleSearchText("Seleniumhq");
		Repoter().Info("Search for Seleniumhq site");
	}
}

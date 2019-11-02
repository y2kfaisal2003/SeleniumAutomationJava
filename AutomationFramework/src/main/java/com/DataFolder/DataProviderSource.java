package com.DataFolder;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataProviderSource {
	@DataProvider
	public Object[][] scenarioData(){
	return new Object[][]{{5, "five"}, {6, "six"}};
	}
	
	@DataProvider
	public Object[] scenarioData1(){
	return new Object[]{"five", "six", "seven"};
}
	@DataProvider(name="scenarioData3")
    public static Object[][] getScenarioData(Method method) {       
        String testCase = method.getName();
        if ("scenario1".equals(testCase)) {
            return new Object[][]{{"Scenario1 data"}};
        } else if ("scenario2".equals(testCase)) {
            return new Object[][]{{"Scenario2 data"}};
        } else {
            return new Object[][]{{"Common scenario data"}};
        }
    }   
     
    @DataProvider(name="TestType")
    public static Object[][] getTestTypeData(ITestContext context) {        
        String testName = context.getClass().getName();
        if ("IntegrationLevel".equals(testName)) {
            return new Object[][]{{"Integration test data"}};
        } else if ("AcceptanceLevel".equals(testName)) {
            return new Object[][]{{"Acceptance test data"}};
        } else {
            return new Object[][]{{"Common test data"}};
        }
    }   
    
    @DataProvider(name="TestMethod") 
    public Object[][] getEntityData() {
    	TestEntity testEntity = new TestEntity();
    	testEntity.setTitleValue("Google");
    	testEntity.setSearchValue("Seleniumhq");
        return new Object[][]{{testEntity}};
    }
}

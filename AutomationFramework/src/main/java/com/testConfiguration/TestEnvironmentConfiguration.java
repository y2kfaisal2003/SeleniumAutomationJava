package com.testConfiguration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestEnvironmentConfiguration {
	
	private static Properties prop= new Properties();
	
	public static String getTestConfiguration(String strEnvironmentConfiguration) throws IOException{
		try{
			FileReader fs = new FileReader(new File(System.getProperty("user.dir")+"/Configuration/environmentConfiguration.properties"));
			prop.load(fs);
		}catch(Exception e){
			throw new IOException(e.getMessage());
		}
		return prop.getProperty(strEnvironmentConfiguration);
	}
}

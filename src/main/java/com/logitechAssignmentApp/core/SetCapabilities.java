package com.logitechAssignmentApp.core;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;


public class SetCapabilities {
	public String platformName;
	public String platformVersion;
	public String deviceName;
	public String appPackage;
	public String appActivity; 
	public String envURL;
	public String appPath;
	
	public HashMap<String, String> readPropertyFile(String propertyFileName) throws IOException{
	HashMap<String, String> propertyValue = new HashMap<String, String>();
	try
	{
			
		InputStream input = new FileInputStream(System.getProperty("user.dir") + "\\Properties\\"+ propertyFileName+".properties");
		Properties prop = new Properties();
		prop.load(input);
		
		//Print and Load Property files in HashMap Property Files
		System.out.println("Platform Name- " + prop.getProperty("platformName"));
		System.out.println("Platform Version- " + prop.getProperty("platformVersion"));
		System.out.println("Device Name- " + prop.getProperty("deviceName"));
		System.out.println("App package- " + prop.getProperty("appPackage"));
		System.out.println("App Activity- " + prop.getProperty("appActivity"));
		System.out.println("App path- " + System.getProperty("user.dir")+  prop.getProperty("appPath"));
		System.out.println(prop.getProperty("appiumURL"));
		
		//Put properties in HashMap
		propertyValue.put("platformName", prop.getProperty("platformName"));
		propertyValue.put("platformVersion", prop.getProperty("platformVersion"));
		propertyValue.put("deviceName", prop.getProperty("deviceName"));
		propertyValue.put("appPackage", prop.getProperty("appPackage"));
		propertyValue.put("appActivity", prop.getProperty("appActivity"));
		propertyValue.put("envURL", prop.getProperty("appiumURL"));
		propertyValue.put("appPath", prop.getProperty("appPath"));
	}
	catch(Exception e)
	{
		System.err.println("File not found/Invalid file name provided");
		e.printStackTrace();
	}
	return propertyValue;
	}
	
	//Set Final Capabilities for parameters
	public  final void setFinalParameters() throws IOException {
		HashMap<String, String> getPropertyFromMap = new HashMap<String, String>();
		getPropertyFromMap = readPropertyFile("config");
		platformName = getPropertyFromMap.get("platformName");
		platformVersion= getPropertyFromMap.get("platformVersion");
		deviceName= getPropertyFromMap.get("deviceName");
		appPackage= getPropertyFromMap.get("appPackage"); 
		appActivity= getPropertyFromMap.get("appActivity"); 
		envURL= getPropertyFromMap.get("envURL");	
		appPath=System.getProperty("user.dir")+ getPropertyFromMap.get("appPath");
	}
	
	
}

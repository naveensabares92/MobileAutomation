package com.logitechTest.LogitechTestAndroid;

import org.testng.annotations.Test;

public class dummyTest {

	@Test
	public static void main() {
		String propertyFileName = "config";
		System.err.println(System.getProperty("user.dir") + "\\Properties\\"+ propertyFileName+".properties");
	}
}

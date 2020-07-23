package com.appium.framework.AppiumJavaFramework;
import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import resources.CommonUtils;

public class FailedTest extends GeneralStoreBase{
	
	CommonUtils commonUtils;
	
	@Test
	public void firstTestDemo() throws InterruptedException, IOException {
		
		String testDataPath = System.getProperty("user.dir")+"//TestData//APIDemos//testdata.properties";
		FailedTest ft = new FailedTest();
		AndroidDriver< AndroidElement> driver = ft.capability("APIDemos");
		String actual= driver.findElementByXPath("//android.widget.TextView[@index=8]").getText();
		Assert.assertEquals(actual, commonUtils.getPropertyValue(testDataPath, "PreferenceMenu"));

		
	}

}

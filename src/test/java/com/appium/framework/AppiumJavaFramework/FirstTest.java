package com.appium.framework.AppiumJavaFramework;
import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class FirstTest extends GeneralStoreBase{
	
	@Test
	public void firstTestDemo() throws InterruptedException, IOException {
		
		FirstTest ft = new FirstTest();
		AndroidDriver< AndroidElement> driver = ft.capability("APIDemos");
		driver.findElementByXPath("//android.widget.TextView[@index=8]").click();
		driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		driver.findElementByXPath("//android.widget.CheckBox[@checkable='true']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='WiFi settings']").click();
		driver.findElementById("android:id/edit").sendKeys("Test1234");
		driver.findElementById("android:id/button1").click();
		
	}

}

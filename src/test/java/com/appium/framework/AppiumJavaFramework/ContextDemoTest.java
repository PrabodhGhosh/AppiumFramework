package com.appium.framework.AppiumJavaFramework;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.GeneralStoreFormPage;
import resources.CommonUtils;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;


public class ContextDemoTest extends GeneralStoreBase {
	
	GeneralStoreFormPage generalStoreFormPage;
	CommonUtils commonUtils;
	
	@Test
	
	public void totalValidation() throws InterruptedException, IOException {
		ContextDemoTest csx = new ContextDemoTest();
		AndroidDriver<AndroidElement> driver = csx.capability("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		generalStoreFormPage= new GeneralStoreFormPage(driver);
		commonUtils = new CommonUtils();
		generalStoreFormPage.femaleRadio.click();
		driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();
		//Click on the select box
		driver.findElementById("android:id/text1").click();
		//Scroll until element is visible
		commonUtils.scrollToText(driver, "Argentina");
		//Select the value
		driver.findElementByXPath("//android.widget.TextView[@text='Argentina']").click();
		driver.findElementByXPath("//android.widget.EditText[@text='Enter name here']").sendKeys("Prabodh");
		driver.hideKeyboard();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Nike Blazer Mid '77\"));");
		driver.findElementById("com.androidsample.generalstore:id/productAddCart").click();
		driver.findElementByClassName("android.widget.FrameLayout").click();
		WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));
		TouchAction t = new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();
		AndroidElement terms = driver.findElementById("com.androidsample.generalstore:id/termsButton");
		t.longPress(longPressOptions().withElement(element(terms)).withDuration(ofSeconds(2))).release().perform();
		driver.findElementById("android:id/button1").click();
		driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();
		
		
		//Switch to web view	
		
		
		
		Thread.sleep(7000);
		Set<String> context = driver.getContextHandles();
		for(String con: context)
		{
			System.out.println(con);
		}
		
		driver.context("WEBVIEW_com.androidsample.generalstore");
		
		driver.findElement(By.name("q")).sendKeys("Web View Test");
		
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[contains(text(),'News')]")).click();
		
		Thread.sleep(2000);
		
		//Navigate to Native APP view
		
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.navigate().back();		
		
	}

}

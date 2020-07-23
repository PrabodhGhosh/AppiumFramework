package com.appium.framework.AppiumJavaFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class GeneralStoreBase {

	private AppiumDriverLocalService service;
	private FileInputStream fis;
	private Properties prop;
	public static AndroidDriver<AndroidElement> ad;


	@BeforeMethod
     
	public void startServer() throws IOException, InterruptedException {
		fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\appium\\framework\\AppiumJavaFramework\\global.properties");
		prop = new Properties();
		prop.load(fis);
		
		
		
		// Start server before every test
		service=  AppiumDriverLocalService.buildDefaultService();
		if (!service.isRunning()) {
			service.start();
		}
		
		// Start emulator before every test
		
		if(prop.get("Device").equals("VirtualDevice1")) {
		Runtime.getRuntime().exec((System.getProperty("user.dir")
				+ "\\resources\\Emulator.bat"));
		Thread.sleep(6000);
		}
	}

	@AfterMethod
	// Stop server before every test
	public void stopServer() {
		service.stop();
	}

	public AndroidDriver capability(String appName) throws InterruptedException, IOException {

		fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\appium\\framework\\AppiumJavaFramework\\global.properties");
		prop = new Properties();
		prop.load(fis);

		File apk = new File("resources");
		File fs = new File(apk, (String) prop.get(appName));

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, (String) prop.get("Device"));
		cap.setCapability(MobileCapabilityType.VERSION, "10.0");
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability("chromedriverExecutableDir", apk.getAbsolutePath());
		if (appName.equals("APIDemos")) {
			cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.appium.android.apis");
			cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
		}
		ad = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		if (appName.equals("APIDemos")) {
			ad.findElement(By.xpath("//android.widget.Button[@text='Continue']")).click();
			Thread.sleep(1000);
			ad.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
			Thread.sleep(1000);

		}
		return ad;
	}
	
	
  public void screenShotOnFailure(String testName) throws IOException {
	  
	  File file = ad.getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"//Screenshots//"+testName+".png"));
	  
  }
}

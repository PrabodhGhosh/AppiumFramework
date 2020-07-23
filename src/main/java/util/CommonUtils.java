package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CommonUtils {
	
	
	//Scroll to text
	public void scrollToText(AndroidDriver<AndroidElement> driver, String text) {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
	}
	
	/*
	 * getPropertyValue returns the value of the key provided as an input. It also
	 * takes file path as an input.
	 */

	public static String getPropertyValue(String filepath, String key) {
		String propertyValue = null;
		FileInputStream FIS = null;
		Properties prop = new Properties();
		try {
			FIS = new FileInputStream(filepath);
			prop.load(FIS);
			propertyValue = prop.getProperty(key);

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (FIS != null) {
				try {
					FIS.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

		if (propertyValue == null)
			return ("Key/value is not present");
		else {
			return propertyValue;
		}
	}


}

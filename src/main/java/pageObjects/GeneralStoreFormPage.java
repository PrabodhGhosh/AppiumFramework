package pageObjects;


import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class GeneralStoreFormPage {
	
	public GeneralStoreFormPage(AndroidDriver<AndroidElement> androidDriver) {
		
		PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
		
	}
	
	@AndroidFindBy (id="com.androidsample.generalstore:id/radioFemale")
	public AndroidElement femaleRadio;
	

}

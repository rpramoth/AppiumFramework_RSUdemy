package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {

	public LoginPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
		
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	public WebElement countryDrpDwn;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Enter name here']")
	public WebElement nameField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")	
	public WebElement radioBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public AndroidElement submitBtn;
	
}

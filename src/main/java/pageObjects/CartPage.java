package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage {

	public CartPage(AndroidDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalPriceInCart;

	@AndroidFindBy(className = "android.widget.CheckBox")
	public AndroidElement checkBoxInCart;

	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	public AndroidElement tncLink;

	@AndroidFindBy(id="android:id/button1")
	public AndroidElement closeTnc;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Visit to the website to complete purchase']")
	public AndroidElement visitWebPage;

}

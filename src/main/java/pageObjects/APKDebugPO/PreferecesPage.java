package pageObjects.APKDebugPO;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PreferecesPage {

	public PreferecesPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}


	@AndroidFindBy(id="android:id/checkbox")
	public AndroidElement checkbox;

	@AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[2]")
	public AndroidElement selectOption;

	@AndroidFindBy(className = "android.widget.EditText")
	public AndroidElement sendText;
	
	@AndroidFindBy(className = "android.widget.Button")
	public List<AndroidElement> selectBtn;
}

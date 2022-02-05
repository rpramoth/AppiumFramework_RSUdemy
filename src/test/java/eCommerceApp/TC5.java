package eCommerceApp;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import pageObjects.CartPage;
import pageObjects.LoginPage;
import pageObjects.ProductPage;
import utilties.ReusableMethods;

/**
 * @author Pramoth
 *
 */
// Validate the long press in the checkout page

public class TC5 extends ReusableMethods{

	@Test
	public void test1() throws IOException, InterruptedException {
	//	startServer();
		AndroidDriver<AndroidElement> driver = desiredCapabilities("GlobalStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.countryDrpDwn.click();
		ReusableMethods.scrollToText(driver, "Argentina");
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))");
		driver.findElementByAndroidUIAutomator("text(\"Argentina\")").click();
		lp.nameField.sendKeys("Hello");
		driver.hideKeyboard();
		lp.radioBtn.click();
		lp.submitBtn.click();
		String[] productsNeeded = {"Jordan 6 Rings","PG 3"};
		ProductPage productPage = new ProductPage(driver);
		double productSum=0;
		for(int l=0;l<productsNeeded.length;l++) {
			ReusableMethods.scrollToText(driver, productsNeeded[l]);
			int count = productPage.productLists.size();
			System.out.println("Count is "+count);
			for(int i =0;i<count;i++) {
				String productName = productPage.productLists.get(i).getText();
				if(productName.equals(productsNeeded[l])) {
					String price=productPage.productPrice.get(i).getText();
					System.out.println(price);
					productPage.addToCartBtn.get(i).click();		
					productSum+=Double.valueOf(price.substring(1));
				}
			}
		}
		System.out.println("Prices in the product page = "+productSum);
		productPage.cartBtn.click();
		CartPage cp = new CartPage(driver);
		
		String pricesInCart= cp.totalPriceInCart.getText();
		double priceInCart = Double.valueOf(pricesInCart.substring(1));
		System.out.println("Prices in the cart page = "+priceInCart);

		Assert.assertEquals(productSum, priceInCart);

		AndroidElement checkBox = cp.checkBoxInCart;
		AndroidElement tnc = cp.tncLink;

		TouchAction ta = new TouchAction(driver);
		ta.tap(tapOptions().withElement(element(checkBox))).perform();		
		ta.longPress(LongPressOptions.longPressOptions().withElement(element(tnc)).withDuration(ofSeconds(2))).release().perform();

		System.out.println("Terms and Condition is displayed "+driver.findElementByAndroidUIAutomator("text(\"Terms Of Conditions\")").isDisplayed());
		cp.closeTnc.click();

		cp.visitWebPage.click();
		
//		stopServer();


	}
}

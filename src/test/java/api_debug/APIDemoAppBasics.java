package api_debug;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import pageObjects.APKDebugPO.DependecyPage;
import pageObjects.APKDebugPO.HomePage;
import pageObjects.APKDebugPO.PreferecesPage;
import utilties.ReusableMethods;

public class APIDemoAppBasics extends ReusableMethods{

	@Test
	public void apiDemoApp() throws IOException, InterruptedException {		
		AndroidDriver<AndroidElement> driver = desiredCapabilities("API_Debug");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		HomePage hp = new HomePage(driver);
		hp.preferenceLink.click();
		DependecyPage d = new DependecyPage(driver);
		d.dependencies.click();

		PreferecesPage pp = new PreferecesPage(driver);
		pp.checkbox.click();
		pp.selectOption.click();
		pp.sendText.sendKeys("Pramoth");
		List<AndroidElement> list=pp.selectBtn;
		for(AndroidElement e: list) {
			if(e.getText().equals("OK")) {
				e.click();
			}
		}
	}

}

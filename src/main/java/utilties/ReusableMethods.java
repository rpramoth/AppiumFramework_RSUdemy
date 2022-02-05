package utilties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class ReusableMethods {
	AppiumDriverLocalService service;
	static 	AndroidDriver<AndroidElement> driver;
	public static AndroidDriver<AndroidElement> desiredCapabilities(String appName) throws IOException, InterruptedException {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/config.properties");
		Properties prop = new Properties();
		prop.load(fis);
		File file = new File(new File("src/main/java/resources"),prop.getProperty(appName));
		// we use desiredCapabilities to define the device name and the app name
		DesiredCapabilities ds = new DesiredCapabilities();
		//		if(prop.getProperty("device").equalsIgnoreCase("PramothApp")) {
		//			ReusableMethods.startEmulator();
		//		}
		ds.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		ds.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,15);
		ds.setCapability(MobileCapabilityType.VERSION, "9.0");
		ds.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
		ds.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("device"));
		ds.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Uiautomator2");
		ds.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),ds);



		return driver;
	}

	public static AndroidDriver<AndroidElement> browserDesiredCapabilities() throws MalformedURLException {
		DesiredCapabilities ds = new DesiredCapabilities();
		ds.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		ds.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		ds.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),ds);
		return driver;


	}


	public static void scrollToText(AndroidDriver driver,String input) {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+input+"\"))");

	}

	public static void startEmulator() throws IOException, InterruptedException {
		Runtime r =Runtime.getRuntime();
		r.exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\EmulatorLaunch.bat");
		Thread.sleep(15000);
	}


	public void startServer() {
		service=AppiumDriverLocalService.buildDefaultService();
		service.start();

	}
	public void stopServer() {
		service=AppiumDriverLocalService.buildDefaultService();
		service.stop();

	}

	public static void takeScreenshot(String testName) throws IOException {
		File target = new File("target/"+testName+".jpg");
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, target);
	}


}

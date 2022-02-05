

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
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

public class ReusableMethods_Cloud {
	AppiumDriverLocalService service;
	static AndroidDriver<AndroidElement> driver;
	public static AndroidDriver<AndroidElement> desiredCapabilities(String appName) throws IOException, InterruptedException {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/config.properties");
		Properties prop = new Properties();
		prop.load(fis);

		DesiredCapabilities ds = new DesiredCapabilities();
		ds.setCapability("browserstack.user", "techaddict_PXljrz");
		ds.setCapability("browserstack.key", "LxEjukDTAE79JByD4Cdk");
		if(appName.equalsIgnoreCase("API_Debug")) {
			ds.setCapability("app", "bs://f0a7509220c8b2922c78d8325f1b1cba4871b642");
			ds.setCapability("device", "Huawei P30");
			ds.setCapability("os_version", "9.0");
		}else {
			ds.setCapability("app", "bs://ca9359d77c3dac96579e4b6779ca7660c978f4ee");
			ds.setCapability("device", "Oppo Reno 3 Pro");
			ds.setCapability("os_version", "10.0");
		}

		ds.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,15);
		ds.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
		ds.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Uiautomator2");
		driver = new AndroidDriver<AndroidElement>(
				new URL("http://hub.browserstack.com/wd/hub"), ds);



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

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public AppiumDriverLocalService startServer() {
		boolean flag=	checkIfServerIsRunnning(4723);
		if(!flag)
		{

			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;

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

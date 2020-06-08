package com.maersk.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.maersk.PageHelper.MaerskPageHelper;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;


public abstract class DriverTestCase{


	public PropertyReader propertyReader;
	public MaerskPageHelper maerskPageHelper;
	public AndroidDriver<MobileElement> driver;
	public static AppiumDriverLocalService appiumService;
	public static String appiumServiceUrl;
	public static DesiredCapabilities capabilities;

	
	@Parameters({"deviceUDID","platformVersion","appName_with_apk_extension"})
	@BeforeClass
	public void setUp(String deviceUDID,String platformVersion,String appName_with_apk_extension) {
		
		//Clear all screenshots
		clearAllScreenShots("Screenshots");
		clearAllScreenShots("attachedScreenshot");
		
		//Start appium server with set of capabilities
		startServer(deviceUDID, platformVersion, appName_with_apk_extension);
		//ebayPagehelper = new eBayPageHelper(driver);
		propertyReader = new PropertyReader();
		maerskPageHelper = new MaerskPageHelper(driver);
		
	}
	
	@AfterMethod
	public void afterEachTestMethod(ITestResult result) throws IOException {

		if (ITestResult.FAILURE == result.getStatus()) {
			TakesScreenshot ts=(TakesScreenshot)getDriver();
		    File source=ts.getScreenshotAs(OutputType.FILE);
		    String location =System.getProperty("user.dir")+"\\attachedScreenshot\\"+result.getName()+".png";
		    File screenshotLocation =new File (location);
		    FileUtils.copyFile(source, screenshotLocation);
		    String path = "<img src=\"file://" + location + "\" alt=\"\" width='300' height='500'/>";
		    Reporter.log(path);
		}
		
	}
	
	@AfterTest
	public void aftertest(){
		maerskPageHelper = null;
		stopServer();
	}
	
	public AndroidDriver getDriver() {
		return driver;
	}
	
	public void startServer(String deviceUDID,String platformVersion, String appName_with_apk_extension) {
		
		appiumService = AppiumDriverLocalService.buildDefaultService();
		appiumService.start();
		appiumServiceUrl = appiumService.getUrl().toString();
        System.out.println("Appium Service Address : - "+ appiumServiceUrl);
		
    	File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/app/Android");
        File app = new File (appDir, appName_with_apk_extension);
        
        //Set Capabilities
        
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("noReset", true);
        capabilities.setCapability(MobileCapabilityType.UDID, deviceUDID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "35ff88ab");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        capabilities.setCapability("appWaitActivity", "SplashActivity, SplashActivity,OtherActivity, *, *.SplashActivity");
        
		try {
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			capabilities.setCapability("adbPort", "5038");
			driver=new AndroidDriver<MobileElement>(new URL(appiumServiceUrl),capabilities);
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stopServer() {
    	appiumService.stop();
	}
	
	public String getPath() {
		String path = "";
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
		return path;
	}

	// delete all the file under screenshots folder.
	public void clearAllScreenShots(String folderName) {
		String path = getPath();
		File directory = new File(path + "//"+folderName);
		for (File f : directory.listFiles())
			f.delete();
	}
	
	public void startExecutionOfNewTest(String str_negativeMessage){
		Reporter.log("<font color='blue'>"+str_negativeMessage+"</font>");
	}

}

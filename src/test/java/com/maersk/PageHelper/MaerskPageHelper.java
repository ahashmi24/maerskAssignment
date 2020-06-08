package com.maersk.PageHelper;


import java.io.IOException;
import java.util.List;
import org.testng.Assert;
import org.testng.Reporter;

import com.maersk.locators.LocatorReader;
import com.maersk.util.DriverHelper;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;




public class MaerskPageHelper extends DriverHelper {
	
private LocatorReader locator;	


	public MaerskPageHelper(AndroidDriver<MobileElement> driver) {
		super(driver);	
		// Load locator page.
		locator = new LocatorReader("MaerskLocators.xml");
	}

	public void tapOnScheduleTab(){
		
		try {
			
			WaitForElementPresent(locator.getLocator("homePage.StartLabel"), 120);
			
			if (isElementPresent(locator.getLocator("homePage.scheduleTab"))) {
				
				attachedToReport("landingScreen");
				
				List<MobileElement> list_tabs = getDriver().findElements(ByLocator(locator.getLocator("homePage.scheduleTab")));
				list_tabs.get(1).click();
				
				positiveComment("Tap on 'Schedule' tab.");
				
			} else {
				
				negativeComment("'Schedule' tab is not present.");
				
				Assert.assertTrue(isElementPresent(locator.getLocator("homePage.scheduleTab")), "'Schedule' tab is not present.");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			Assert.assertTrue(false, e.getMessage());
		}
		
	}
	
	public void tapOnFromField() throws IOException{
		
		try {
			WaitForElementPresent(locator.getLocator("homePage.FromField"), 50);
			
			attachedToReport("fromfield");
			
			Assert.assertTrue(isElementPresent(locator.getLocator("homePage.FromField")), "'From' field is not present.");
			
			clickOn(locator.getLocator("homePage.FromField"));
			
			positiveComment("Tap on 'From' field.");
			
			
		} catch (Exception e) {
			
			attachedToReport("error");
			
			negativeComment("Error Occurred: "+e.getMessage());
			
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	public void enterTextInToSearchField(String str_input) throws IOException{
		
		try {
			WaitForElementPresent(locator.getLocator("homePage.SearchField"), 50);
			
			attachedToReport("searchField1");
			
			Assert.assertTrue(isElementPresent(locator.getLocator("homePage.SearchField")), "'Search' field is not present.");
			
			sendKeys(locator.getLocator("homePage.SearchField"), str_input);
			
			positiveComment("Enter '"+str_input+"' into search field.");
			
			
		} catch (Exception e) {
			attachedToReport("error");
			
			negativeComment("Error Occurred: "+e.getMessage());
			
			Assert.assertTrue(false, e.getMessage());
		}
		
	}
	
	public void selectLocationFromDropDown(String str_location) throws IOException{
		
		try {
			
			WaitForElementPresent(locator.getLocator("homePage.AutoDropList"), 60);
			
			attachedToReport("dropList1");
			
			Assert.assertTrue(isElementPresent(locator.getLocator("homePage.AutoDropList")), "'Location' droplist not present.");
			
			List<MobileElement> list_location = getDriver().findElements(ByLocator(locator.getLocator("homePage.AutoDropList")));
			
			for (MobileElement text : list_location) {
				
				if (text.getText().equalsIgnoreCase(str_location)) {
					
						text.click();
						positiveComment("Tap on '"+str_location+"' location from list.");
						break;
					
					
				}
			}
			
			
		} catch (Exception e) {
			
			attachedToReport("error");
			
			negativeComment("Error Occurred: "+e.getMessage());
			
			e.printStackTrace();
			
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	public void tapOnToField() throws IOException{
		
		try {
			WaitForElementPresent(locator.getLocator("homePage.ToField"), 50);
			
			Assert.assertTrue(isElementPresent(locator.getLocator("homePage.ToField")), "'To' field is not present.");
			
			clickOn(locator.getLocator("homePage.ToField"));
			
			positiveComment("Tap on 'To' field.");
			
			
		} catch (Exception e) {
			
			attachedToReport("error");
			
			negativeComment("Error Occurred: "+e.getMessage());
			
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	public void tapOnSearchButton() throws IOException{
		
		try {
			WaitForElementPresent(locator.getLocator("homePage.SearchButton"), 80);
			
			Assert.assertTrue(isElementPresent(locator.getLocator("homePage.SearchButton")), "'Search' button not present.");
			
			clickOn(locator.getLocator("homePage.SearchButton"));
			
			positiveComment("Tap on 'Search' button.");
			
		} catch (Exception e) {
			
			attachedToReport("error");
			
			negativeComment("Error Occurred: "+e.getMessage());
			
			Assert.assertTrue(false, e.getMessage());
		}
		
	}
	
	public void getResultCount() throws IOException{
		
		try {
			
			WaitForElementPresent(locator.getLocator("homePage.SailingsCount"), 80);
			
			attachedToReport("sailings");
			
			Assert.assertTrue(isElementPresent(locator.getLocator("homePage.SailingsCount")), "'Sailing' counts not present.");
			
			Reporter.log("Sailings count: "+getText(locator.getLocator("homePage.SailingsCount")));
			
		} catch (Exception e) {
			
			attachedToReport("error");
			
			negativeComment("Error Occurred: "+e.getMessage());
			
			Assert.assertTrue(false, e.getMessage());
		}
		
	}
}

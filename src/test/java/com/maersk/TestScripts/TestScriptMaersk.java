package com.maersk.TestScripts;

import java.io.IOException;

import org.testng.annotations.Test;

import com.maersk.util.DriverTestCase;

public class TestScriptMaersk extends DriverTestCase {

	@Test
	public void searchForShipment() throws IOException {

		startExecutionOfNewTest(
				"Start execution of Test Method '" + Thread.currentThread().getStackTrace()[1].getMethodName() + "'");
		
		maerskPageHelper.tapOnScheduleTab();
		
		maerskPageHelper.tapOnFromField();
		
		maerskPageHelper.enterTextInToSearchField(propertyReader.readApplicationFile("source"));
		
		maerskPageHelper.selectLocationFromDropDown(propertyReader.readApplicationFile("exactSource"));
		
		maerskPageHelper.tapOnToField();
		
		maerskPageHelper.enterTextInToSearchField(propertyReader.readApplicationFile("destination"));
		
		maerskPageHelper.selectLocationFromDropDown(propertyReader.readApplicationFile("exactDestination"));
		
		maerskPageHelper.tapOnSearchButton();
		
		maerskPageHelper.getResultCount();

		maerskPageHelper.getSailingsName();

	}

	
}

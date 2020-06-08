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
		
		maerskPageHelper.enterTextInToSearchField("Mundra");
		
		maerskPageHelper.selectLocationFromDropDown("Mundra, GUJARAT");
		
		maerskPageHelper.tapOnToField();
		
		maerskPageHelper.enterTextInToSearchField("Jebel");
		
		maerskPageHelper.selectLocationFromDropDown("Jebel Ali");
		
		maerskPageHelper.tapOnSearchButton();
		
		maerskPageHelper.getResultCount();

		maerskPageHelper.getSailingsName();

	}

	
}

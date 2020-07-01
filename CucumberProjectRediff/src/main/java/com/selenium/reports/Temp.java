package com.selenium.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Temp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ExtentReports rep= ExtentManager.getInstance("C:\\Ravibabu\\Selenium\\Reports\\");
		ExtentTest test= rep.createTest("Scenario A");
		test.log(Status.INFO, "Starting Test A");
		test.log(Status.INFO, "Executing Test A");
		test.log(Status.FAIL, "Test A Failed");
		
		 test= rep.createTest("Scenario B");
			test.log(Status.INFO, "Starting Test B");
			test.log(Status.INFO, "Executing Test B");
			test.log(Status.PASS, "Test B Pass");
			
			test= rep.createTest("Scenario C");
			test.log(Status.INFO, "Starting Test C");
			test.log(Status.INFO, "Executing Test C");
			test.log(Status.SKIP, "Test C Pass");
			
			test= rep.createTest("Scenario D");
			test.log(Status.INFO, "Starting Test D");
			test.log(Status.INFO, "Executing Test D");
			test.log(Status.PASS, "Test D Pass");
			
			test= rep.createTest("Scenario E");
			test.log(Status.INFO, "Starting Test E");
			test.log(Status.INFO, "Executing Test E");
			test.log(Status.PASS, "Test E Pass");
			
			test= rep.createTest("Scenario F");
			test.log(Status.INFO, "Starting Test F");
			test.log(Status.INFO, "Executing Test F");
			test.log(Status.FAIL, "Test F Pass");
			
		rep.flush();
		
		

	}

}

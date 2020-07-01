package com.selenium.steps;

import com.selenium.webdriver.WebConnector;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ApplicationSteps {
	
	WebConnector con;
	public ApplicationSteps(WebConnector con){
		this.con=con;
	}
	
	@Then("^Login Should be success and (.*) on (.*) is correct$")
	public void validateLogin(String name, String nameFiled){
		con.infoLog("Login should be success and " +name+" on" +nameFiled+" is correct");
		con.validateLogin(name,nameFiled);
		con.closeBrowser();
	}
	
	@Then("^Login Should (.*) on the page")
	public void validateLogin(String expectedResult){
		con.infoLog("Login Should "+expectedResult+" on the page");
		con.validateLogin(expectedResult);
		
	}
	

	
}

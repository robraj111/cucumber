package com.selenium.steps;

import java.util.Map;


import com.selenium.webdriver.WebConnector;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class GenericSteps {
	
	WebConnector con;
	
	public GenericSteps(WebConnector con){
		this.con=con;
		
	}
	
	@Before
	public void before(Scenario s){
	System.out.println("####### Scenario started execution ######"+s.getName());	
	con.initReport(s.getName());
	}
	
	@After
	public void after(){
	System.out.println("####### execution completed  ######");
	con.quitRep();
	}

	@Given("^I open (.*)$")
	public void openBrowser(String browserName){
		con.infoLog("Opening browser " +browserName);
		con.openBrowser(browserName);
		
	}
	

	@And("^I navigate to (.*)$")
	public void navigate(String url){
		con.infoLog("I navigate to " +url);
	con.navigate(url);
		
	}
	
	@And("^I eneter (.*) in (.*)$")
	public void enterEmail(String email, String emailfield){
		con.infoLog("I enter " + email);
		con.typeValue(emailfield,email);
		
		
	}
	
	
	@And("^I enter (.*) in (.*)$")
	public void enterPassword(String password, String passfield){
		con.infoLog("i enter " +password);
		con.typeValue(passfield,password);
		
	}
		
	@And("^I click on (.*) button$")
	public void clickOnButton(String submit){
		con.infoLog("I CLick on"+submit+ "Buttton");
		con.click(submit);
				
	}

	
	@And ("^I login Inside Application$")
	public void logIn(Map<String,String> data){
		con.infoLog("I Login Inside Application");
		con.infoLog(data.get("Username"));
		con.infoLog(data.get("Password"));
		con.login(data.get("Username"),data.get("Password"));
	//	con.acceptAlertIfPresent();
		
		
	}
	
	@Then("^I logout by selecting (.*) button$")
	public void logout(String signout){
		con.infoLog("I Logout by selecting"+signout+"button");
		con.click(signout);
		System.out.println("logout successfull");
		con.closeBrowser();
	}
	

	
	
	


}

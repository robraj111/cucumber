package com.selenium.runner;

import io.cucumber.junit.CucumberOptions;

@CucumberOptions(
		dryRun=false,  // scenarios will not execute, it just inform whether every thing is fine or not.
		strict= true,  //if step definition corresponding to scenario  is not found then  scenario execution  be stopped, if it is flase   then scenarios will be executed 
		monochrome=true,
		features= {"src/test/resources/"}, // all feature file under this folder, can be arrayed  folder by (,) separator
		glue = {"com.pselenium.cucumber"}, //all feature file under this folder, can be arrayed  folder by (,) separator
	    plugin= { 
				 "html:target/site/cucumber-html",  // html reort generation
				 "json:target/cucumber1.json"} // Json report generation
		//tags= "@BookHotel, @BookFlight"  // only specified scenarios/ feature can be executed.
)


public class MyJunitRunner {

}

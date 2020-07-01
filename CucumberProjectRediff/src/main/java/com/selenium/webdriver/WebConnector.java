package com.selenium.webdriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.reports.ExtentManager;

import static org.assertj.core.api.Assertions.assertThat;
public class WebConnector {

	 WebDriver driver;
	 public Properties prop;
	 public ExtentReports rep;
	 public ExtentTest scenario;
	 
	 public WebConnector() {
	
		 // Initialise properties object.
		 
		 if(prop==null)
			 
	 try {
			 prop=new Properties();
		 FileInputStream fs= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties");
		 prop.load(fs);
		 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 

	 
	 public void openBrowser(String browserName){
		 if(browserName.equals("Mozilla")){
			 System.setProperty("webdriver.gecko.driver", "C:\\Ravibabu\\Selenium\\Browsers\\Test\\geckodriver.exe");
			 driver= new FirefoxDriver();
		 }
		 if(browserName.equals("Chrome")){
			 System.setProperty("webdriver.chrome.driver", "C:\\Ravibabu\\Selenium\\Browsers\\Test\\chromedriver.exe");

			 driver= new ChromeDriver();
		 }
		 if(browserName.equals("IE")){
			 
			 driver= new InternetExplorerDriver();
		 }
		 
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	 }
	 
	 public void navigate( String urlKey){
		 driver.get(prop.getProperty(urlKey));
	 }

	 
	 public void click(String submit){
		 getObject(submit).click();
	 }
	 
	 public void typeValue(String objectKey, String data ){
		 getObject(objectKey).sendKeys(prop.getProperty(data));;
		 
	 }
	 
	 public void type(String objectKey, String data ){
		 getObject(objectKey).sendKeys(data);;
		 
	 }
	
	public void validateLogin(String nameKey, String objectKey) {
		//implement later
		String nameVal=getObject(objectKey).getText();
		System.out.println(nameVal);

		SoftAssertions softAssert= new SoftAssertions();
		softAssert.assertThat(nameVal).isEqualTo(prop.getProperty(nameKey));
		softAssert.assertAll();
		
	}
	
	
	public WebElement getObject(String objectKey){
		WebElement e= null;
		WebDriverWait wait = new WebDriverWait(driver,10);
		
	try{
		
		if(objectKey.endsWith("_xpath")){
			e=driver.findElement(By.xpath(prop.getProperty(objectKey)));  // to check whether element present or not
			// to wait until element present
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty(objectKey))));
		}else if(objectKey.endsWith("_name")){
			e=driver.findElement(By.name(prop.getProperty(objectKey)));  // to check whether element present or not
			// to wait until element present
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty(objectKey))));
		} else if(objectKey.endsWith("_id")){
				e=driver.findElement(By.id(prop.getProperty(objectKey)));  // to check whether element present or not
				// to wait until element present
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty(objectKey))));
				}
		
	} catch(Exception ex){
			ex.printStackTrace();
		reportFailure(ex.getMessage());
		closeBrowser();
			
		}
		
		return e;
	}
	
	
	//true - present
	//false not present
	public boolean isElementPresent(String objectKey){
		List<WebElement> e= null;
		
			
			if(objectKey.endsWith("_xpath")){
				e=driver.findElements(By.xpath(prop.getProperty(objectKey)));  // to check whether element present or not
								
			}else if(objectKey.endsWith("_name")){
				e=driver.findElements(By.name(prop.getProperty(objectKey)));  // to check whether element present or not
								
			} else if(objectKey.endsWith("_id")){
					e=driver.findElements(By.id(prop.getProperty(objectKey)));  // to check whether element present or not
					
			}
			
		 if(e.size()==0)
		return false;
		 else 
			 return true;
		
	}
	
	public void validateLogin(String expectedResult){
		boolean result=isElementPresent("prtfolioLocation_id");
		String actualResult="";
		if(result)
			actualResult="success";
		else
			actualResult="failure";
		
         infoLog("expectedResult was :"+expectedResult);
         infoLog("actualResult was :"+actualResult);
		if(!expectedResult.equals(actualResult))
			reportFailure("Scenario flure");
		else
			infoLog("Scenario pass");
	}

	public void login(String username, String password) {
		// TODO Auto-generated method stub
		type("emailField_xpath",username);
		type("passwordField_xpath",password);
		click("Submit_xpath");
		
		
	}
	

	
	public  boolean isAlertPresent(){
	      try{
	    	  
			WebDriverWait wait = new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.alertIsPresent());
	          driver.switchTo().alert();
	          return true;
	      }catch(NoAlertPresentException ex){
	          return false;
	      }
	}
	
	public void acceptAlertIfPresent(){
		
	    if(isAlertPresent()){
		try{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			
			
			} catch(Exception ex){
			//not present
				}
		
	    }
	    
 
	}
	
	
	
	 public void closeBrowser(){
		 
		 driver.close();
		  
	  }
	 
		 
	 
	 
// ################*******************LOGING*********************##########################
	 
	 public void infoLog(String msg){
		 scenario.log(Status.INFO, msg);
		 
	 }
	 
	 
	 public void reportFailure(String errorMsg){
		 //1. Fail in Extent report
		 scenario.log(Status.FAIL, errorMsg);
		 //Take screen shot and put in reports
		 takeSceenShot();
		 // Fail in Cucumber as well
		  assertThat(false);
		 
	 }

	 // Take screenshot
	 public void takeSceenShot(){
			// fileName of the screenshot
			Date d=new Date();
			String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
			// take screenshot
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				// get the dynamic folder name
				FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
				//put screenshot file in reports
				scenario.log(Status.FAIL,"Screenshot-> "+ scenario.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
//#######################*********** Reporting*******************############################
	 
	 public void initReport(String scenarioName ){
		 rep=ExtentManager.getInstance(prop.getProperty("reportpath"));
		 scenario=rep.createTest(scenarioName);
		 
		 scenario.log(Status.INFO, " Starting"+scenarioName);
		
	 }

	 // to flush the report at the end of the test
	public void quitRep() {

		if(rep!=null)
			rep.flush();
	}
	 
	//##############################################################################################
	 
	 
}

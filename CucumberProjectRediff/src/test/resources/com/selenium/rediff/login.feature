#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@Login
Feature: Logging into rediff Money
  

  @Login1
  Scenario: Loggin into rediffmoney
    Given I open Chrome
    And I navigate to loginURL
    And I enter email in emailField_xpath
    And I enter password in passwordField_xpath
    And I click on Submit_xpath button
    Then Login Should be success and name on accountNameField_xpath is correct
    
    
  @Login2
  Scenario Outline: Loggin into rediffmoney page
    Given I open <Browser>
    And I navigate to loginURL
    And I login Inside Application
    |Username|ravibabu408|
    |Password|kanapa|
    Then Login Should <Result> on the page
    Then I logout by selecting SignOut_xpath button
    
    Examples:
    |Browser|Result |
    |Mozilla|success|
    |Chrome|success|
    
    
    
 
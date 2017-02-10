Feature: Isureon Home Page Test 

Background:
Given I visit home page of insureon	

	@home	
	Scenario: Verify Home Page Header Elements
		Then I verify all the header navigation elements are displayed and navigating as expected
		
		
	@home	
	Scenario: Verify Home Page Data Containers
		Then I verify all the data containers are displayed
	
	
	@home	
	Scenario: Verify Home Page Contents
		Then I verify all the contents are displayed
	
	
	@home	
	Scenario: Verify Home Page Footer information
		Then I verify all the footer information are displayed and navigating as expected
	
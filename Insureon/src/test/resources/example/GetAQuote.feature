Feature: Isureon Home Page Test 

Background:
Given I visit home page of insureon	

	@getAQuote	
	Scenario Outline: Request a Quote from Home Page
		When I request a quote for '<INDUSTRY>' and '<SERVICE>' from home
		Then my quote progress is started with other questions
		And I start filling the company values and continue to next page
		
	Examples:
	|	INDUSTRY							|	SERVICE					|
	|	Accounting & Finance Professionals	|	Accounting & Auditing	|
	|	Design Professionals				|	Architects				|
	
		
		
	
	
	
	
	
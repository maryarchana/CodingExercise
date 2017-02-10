package example;


import com.ins.pageFactory.BaseFactory;
import com.ins.pageFactory.HomePageFactory;
import com.ins.utils.WebUtil;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class HomeStepDefs {
	
	
	public HomeStepDefs() {
		pageObj = new BaseFactory(DriverManager.globalDriver);
		homeObj = new HomePageFactory(DriverManager.globalDriver);
	}
	
	
	BaseFactory pageObj;
	HomePageFactory homeObj;
	
	
	@Given("I visit home page of insureon")
	public void visitHome() {
		homeObj.getDriver().get("https://www.insureon.com");
		WebUtil.waitForPageLoaded(4000);
	}
	
	@Then("I verify all the header navigation elements are displayed and navigating as expected")
	public void verifyHeaderNav() {
		
		// Check Insureon Icon
		pageObj.verifyHomeIcon();
		
		// Verify the Header Navigation
		pageObj.verifyHeaderNav();
		
		// Verify Get a Quote
		pageObj.verifyGetAQuote();
		pageObj.navigateBack();
	}
	
	
	@And("I verify all the data containers are displayed")
	public void verifyDataContainers() {
		homeObj.verifyContainers();
	}
	
	@And("I verify all the contents are displayed")
	public void verifyContents() {
		homeObj.verifyContents();
	}
	
	@And("I verify all the footer information are displayed and navigating as expected")
	public void verifyFooter() {
		homeObj.verifyFooter();
	}
	
	
}

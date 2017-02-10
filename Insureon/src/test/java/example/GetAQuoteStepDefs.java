package example;

import com.ins.pageFactory.GetAQuoteFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetAQuoteStepDefs {
	
	GetAQuoteFactory quoteObj;
	
	public GetAQuoteStepDefs() {
		quoteObj = new GetAQuoteFactory(DriverManager.globalDriver);
	}
	
	@When("I request a quote for '(.+)' and '(.+)' from home")
	public void requestQuote(String industry, String service) {
		quoteObj.verifyContainer1(industry, service);
	}
	
	
	@Then("my quote progress is started with other questions")
	public void startQuoteProgress() {
		quoteObj.verifyProgressFirstPage();
	}
	
	@And("I start filling the company values and continue to next page")
	public void fillCompanyValues() {
		quoteObj.fillValuesCompany();
	}
}

package com.ins.pageFactory;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ins.utils.CommonUtils;


/**
 * This page object defines all the html dom elements of 
 * Get a Quote pages.  
 *
 */
public class GetAQuoteFactory extends HomePageFactory {

	
	/**
	 * All WebElements are identified by @FindBy annotation
	 */
	public GetAQuoteFactory(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "q-v-1-0")
	private WebElement zipCode;
	
	@FindBy(id = "q-v-1-2")
	private WebElement business;
	
	@FindBy(css = "div[class='progress-bar']")
	private WebElement progressBarWidth;
	
	@FindBy(xpath = "//div[@class='progress-section']/span")
	private WebElement progressPercentage;
	
	@FindBy(id = "img_next")
	private WebElement next;
	
	
	
	
	public void verifyProgressFirstPage() {
		Assert.assertTrue(progressBarWidth.getAttribute("style").contains("5%"));
		Assert.assertEquals(CommonUtils.removeLineBreaks(progressPercentage.getText()), "5%");
	}
	
	public void fillValuesCompany() {
		zipCode.sendKeys("55435");
		business.sendKeys("test");
		click(next);
	}
	
}


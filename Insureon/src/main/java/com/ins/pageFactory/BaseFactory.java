package com.ins.pageFactory;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ins.constants.ENVConstants;
import com.ins.constants.UIPageConstants;
import com.ins.utils.WebUtil;




/**
 *  
 *
 */
public class BaseFactory  {
	
	public BaseFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private WebDriver driver;

	
/*************************** HOME PAGE ELEMENTS **********************************/		
	@FindBy(xpath = "//nav[@class='IonNav']/a")
	private WebElement insureonIcon;
	
	@FindBy(xpath = "//nav[@class='IonNav']/div")
	private WebElement headerNav;
	
	@FindBy(linkText = "Small Business Insurance")
	private WebElement smallBusinessIns;
	
	@FindBy(linkText = "Who We Insure")
	private WebElement whoWeInsure;
	
	@FindBy(linkText = "Resources")
	private WebElement resources;
	
	@FindBy(linkText = "Contact Us")
	private WebElement contactUs;
	
	@FindBy(linkText = "Login")
	private WebElement login;
	
	
	/************* GET QUOTES *******************/
	@FindBy(xpath = "//div[@id='dnn_ctr16058_ModuleContent']/div/nav")
	private WebElement getAQuoteNav;
	
	@FindBy(xpath = "//div[@id='dnn_ctr16058_ModuleContent']/div/nav//button[@class='Nav__callToAction']")
	private WebElement getAQuote;
	
	@FindBy(xpath = "//div[@id='dnn_ctr16059_ModuleContent']//div[@class='navpopout__heading']")
	private WebElement getAQuoteHeadingText;
	
	@FindBy(xpath = "//div[@id='dnn_ctr16059_ModuleContent']//div[@class='navpopout__subhead']")
	private WebElement getAQuoteSubHeadingText;
	
	@FindBy(xpath = "//div[@id='dnn_ctr16059_ModuleContent']//select[contains(@class,'select-industries')]")
	private WebElement getAQuoteSelectIndustry;
	
	@FindBy(xpath = "//div[@id='dnn_ctr16059_ModuleContent']//select[contains(@class,'select-services')]")
	private WebElement getAQuoteSelectServices;
	
	@FindBy(xpath = "//div[@id='dnn_ctr16059_ModuleContent']//button[@class='QuoteButton']")
	private WebElement getAQuoteButton;
	
	
	public void verifyHomeIcon() {
		Assert.assertTrue(checkImages(insureonIcon, UIPageConstants.INSUREON_LOGO));
	}
	
	
	public void verifyGetAQuote() {
		click(getAQuote);
		Assert.assertTrue(getAQuoteNav.getAttribute("class").contains("navpopout--open"));
		Assert.assertEquals(UIPageConstants.GET_A_QUOTE_HEADING, getAQuoteHeadingText.getText());
		Assert.assertEquals(UIPageConstants.GET_A_QUOTE_SUBHEADING, getAQuoteSubHeadingText.getText());
		selectValuesAndGetQuote(null, null, getAQuoteSelectIndustry, getAQuoteSelectServices, getAQuoteButton);
	}
	
	public void verifyHeaderNav() {
		Assert.assertEquals(5, getTotalNavs().size());
		goToSmallBuisness();
		navigateBack();
		
		goToWhoWeServe();
		navigateBack();
		
		goToResources();
		navigateBack();
		
		goToContactUs();
		navigateBack();
		
		goToLogin();
		navigateBack();
		
		goToHome();
	}
	
	public List<WebElement> getTotalNavs() {
		return headerNav.findElements(By.tagName("div"));
	}
	
	
	public void goToSmallBuisness() {
		click(smallBusinessIns);
		Assert.assertEquals(ENVConstants.SMALL_BUSINESS, getDriver().getCurrentUrl());
	}
	
	public void goToWhoWeServe() {
		click(whoWeInsure);
		Assert.assertEquals(ENVConstants.WHO_WE_INSURE, getDriver().getCurrentUrl());
	}
	
	public void goToResources() {
		click(resources);
		Assert.assertEquals(ENVConstants.RESOURCES, getDriver().getCurrentUrl());
	}
	
	public void goToContactUs() {
		click(contactUs);
		Assert.assertEquals(ENVConstants.CONTACT_US, getDriver().getCurrentUrl());
	}
	
	public void goToLogin() {
		click(login);
		Assert.assertEquals(ENVConstants.LOGIN, getDriver().getCurrentUrl());
	}
	
	public void goToHome() {
		click(insureonIcon);
		Assert.assertEquals(ENVConstants.HOME_URL+"/", getDriver().getCurrentUrl());
	}
	
	
	
	
	
	
	
	
	public void selectValuesAndGetQuote(String industry, String service, WebElement industryEle, WebElement servicesEle, WebElement button) {
		if(industry == null) {
			industry = "Computer, Web, IT Services & IT Staffing";
		}
		if(service == null) {
			service = "All Computer, Web & IT Professionals";
		}
		
		Assert.assertTrue(servicesEle.getAttribute("disabled").equals("true"));
		WebUtil.selectOptionByText(industry, industryEle);
		Assert.assertNull(servicesEle.getAttribute("disabled"));

		WebUtil.selectOptionByText(service, servicesEle);
		click(button);
		Assert.assertEquals(ENVConstants.GET_A_QUOTE, getDriver().getCurrentUrl());
	}
	
	
	
	
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public boolean checkImages(WebElement ele, String imageName) {
		String imageSrc = ele.findElement(By.tagName("img")).getAttribute("src");
		boolean isImageDisplayed = false;
		
		if(imageSrc.contains(imageName+".png") || imageSrc.contains(imageName+".jpeg") || imageSrc.contains(imageName+".jpg")) {
			isImageDisplayed = true;
		}
		return isImageDisplayed;
	}
	
	
	public void click(WebElement ele) {
		WebUtil.click(ele, driver);
	}
	
	
	public void navigateBack() {
		getDriver().navigate().back();
		WebUtil.waitForPageLoaded();
	}
}


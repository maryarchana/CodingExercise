package com.ins.pageFactory;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ins.constants.ENVConstants;
import com.ins.constants.UIPageConstants;
import com.ins.utils.CommonUtils;
import com.ins.utils.WebUtil;


/**
 * This page object defines all the html dom elements of Home Page
 *
 */

public class HomePageFactory extends BaseFactory {
	
	public HomePageFactory(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@class='ion-flat-hero']/div")
	private List<WebElement> containers;
	
	@FindBy(css = "div[class='ion-flat-q1-container']")
	private WebElement container1;
	
	@FindBy(css = "div[class='ion-flat-q2-container']")
	private WebElement container2;
	
	@FindBy(css = "div[class='ion-flat-q3-container']")
	private WebElement container3;
	
	@FindBy(css = "div[class='ion-flat-q4-container']")
	private WebElement container4;
	
	@FindBy(css = "div[class='HomepageModal']")
	private WebElement policyPopUp;
	
	@FindBy(xpath = "//div[@class='HomepageModal__content']/h3")
	private WebElement policyHeading;
	
	@FindBy(xpath = "//div[@class='HomepageModal__content']/p")
	private WebElement policyMessage;
	
	@FindBy(xpath = "//div[@class='HomepageModal__content']/figure/figcaption")
	private WebElement policyChoose;
	
	@FindBy(xpath = "//div[@class='HomepageModal__content']/figure/ul/li[1]")
	private WebElement policyOption1;
	
	@FindBy(xpath = "//div[@class='HomepageModal__content']/figure/ul/li[2]")
	private WebElement policyOption2;
	
	@FindBy(css = "div[class='HomepageModal__cancel insuricon-close']")
	private WebElement policyPopUpClose;
	
	@FindBy(xpath = "//div[@class='ionResources_boxWrapper']/a")
	private List<WebElement> contents;
	
	@FindBy(xpath = "//a[@class='ionResources_boxScroller jump-start color0']")
	private WebElement content1;
	
	@FindBy(xpath = "//a[@class='ionResources_boxScroller jump-start color1']")
	private WebElement content2;
	
	@FindBy(xpath = "//a[@class='ionResources_boxScroller jump-start color2']")
	private WebElement content3;
	
	@FindBy(xpath = "//div[@class='ionResources_body']/blockquote")
	private WebElement footerMessage;
	
	@FindBy(linkText = "Small Business Insurance")
	private WebElement footer_small_business;
	
	@FindBy(linkText = "Professional Liability Insurance")
	private WebElement footer_professional_liability;
	
	@FindBy(linkText = "Errors and Omissions Insurance")
	private WebElement footer_errors;
	
	@FindBy(linkText = "General Liability Insurance")
	private WebElement footer_general_liability;
	
	@FindBy(linkText = "Business Owner Policy")
	private WebElement footer_business_policy;
	
	@FindBy(linkText = "Workers' Compensation Insurance")
	private WebElement footer_compensation;
	
	@FindBy(linkText = "INSURANCE RESOURCES")
	private WebElement footer_insurance;
	
	@FindBy(linkText = "Small Business Blog")
	private WebElement footer_business_blog;
	
	@FindBy(linkText = "Frequently Asked Questions")
	private WebElement footer_faq;
	
	@FindBy(linkText = "Articles & eBooks")
	private WebElement footer_ebooks;
	
	@FindBy(linkText = "Insurance Cost Reports")
	private WebElement footer_cost;
	
	@FindBy(linkText = "Site map")
	private WebElement footer_site_map;
	
	@FindBy(linkText = "ABOUT US")
	private WebElement footer_aboutus;
	
	@FindBy(linkText = "Insureon Newsroom")
	private WebElement footer_newsroom;
	
	@FindBy(linkText = "Company History")
	private WebElement footer_company_history;
	
	@FindBy(linkText = "Leadership Team")
	private WebElement footer_leadership;
	
	@FindBy(linkText = "Customer Success Stories")
	private WebElement footer_success_stories;
	
	@FindBy(linkText = "Careers at Insureon")
	private WebElement footer_careers;
	
	@FindBy(linkText = "Cookies and Privacy")
	private WebElement footer_cookies;
	
	@FindBy(linkText = "Terms and Conditions")
	private WebElement footer_tc;
	
	@FindBy(linkText = "Licenses")
	private WebElement footer_licenses;
	
	@FindBy(linkText = "Compensation Disclosure Notice")
	private WebElement footer_disclosure;
	
	
	
	
	public void verifyContainers() {
		Assert.assertEquals(2, containers.size());
		verifyContainer1();
		navigateBack();
		
		verifyContainer2();
		verifyContainer3();
		verifyContainer4();
	}
	
	
	public void verifyContents() {
		Assert.assertEquals(3, contents.size());
		verifyContent(content1, UIPageConstants.CONTENT1_TEXT, "#section0", "15min");
		verifyContent(content2, UIPageConstants.CONTENT2_TEXT, "#section1", "call");
		verifyContent(content3, UIPageConstants.CONTENT3_TEXT, "#section2", "A-rated-partners");
	}
	
	public void verifyFooter() {
		//Assert.assertEquals(UIPageConstants.CONTAINER2_TEXT, footerMessage.getText());
		WebUtil.scrollToEleAndClick(footer_small_business, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_SMALL_BUSINESS, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_professional_liability, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_PROFESSIONAL_LIABILITY, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_errors, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_ERRORS, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_general_liability, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_GENERAL_LIABILITY, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_business_policy, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_POLICY, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_compensation, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_COMPENSATION, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_insurance, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_RESOURCES, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_business_blog, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_BLOG, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_faq, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_FAQ, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_ebooks, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_EBOOKS, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_cost, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_COST, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_site_map, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_SITE, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_aboutus, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_ABOUT_US, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_newsroom, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_NEWS_ROOM, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_company_history, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_HISTORY, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_leadership, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_LEADERSHIP, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_success_stories, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_SUCCESS, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_careers, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_CAREERS, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_cookies, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_COOKIES, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_tc, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_TC, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_licenses, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_LICENSES, getDriver().getCurrentUrl());
		navigateBack();
		
		WebUtil.scrollToEleAndClick(footer_disclosure, getDriver());
		Assert.assertEquals(ENVConstants.FOOTER_DISCLOSURE, getDriver().getCurrentUrl());
		navigateBack();
	}
	
	
	public void verifyContent(WebElement ele, String expectedText, String url, String image) {
		WebUtil.scrollToElement(getDriver(), ele);
		WebElement imageEle = ele.findElement(By.cssSelector("div[class='ionResources_iconWrapper']"));
		Assert.assertTrue(checkImages(imageEle, image));
		
		String text = ele.findElement(By.cssSelector("div[class='ionResources_body']")).getText();
		Assert.assertEquals(expectedText, text);
		
		WebUtil.scrollToEleAndClick(imageEle, getDriver());
		Assert.assertEquals(ENVConstants.HOME_URL + "/" + url, getDriver().getCurrentUrl());
		WebUtil.scrollUp(getDriver());
	}
	
	
	public void verifyContainer1(String industry, String service) {
		String heading = container1.findElement(By.cssSelector("div[class='ion-flat-hassle-free']")).getText();
		String subHeading = container1.findElement(By.cssSelector("div[class='ion-flat-quotes-from-top-carrier']")).getText();
		
		WebElement industryEle = container1.findElement(By.tagName("select-industry")).findElement(By.tagName("select"));
		WebElement servicesEle = container1.findElement(By.tagName("select-service")).findElement(By.tagName("select"));
		WebElement button = container1.findElement(By.cssSelector("button[class='QuoteButton']"));
		
		Assert.assertEquals(UIPageConstants.GET_A_QUOTE_HEADING, heading);
		Assert.assertEquals(UIPageConstants.GET_A_QUOTE_SUBHEADING, subHeading);
		selectValuesAndGetQuote(industry, service, industryEle, servicesEle, button);
	}
	
	
	public void verifyContainer1() {
		verifyContainer1(null, null);
	}
	
	
	void verifyContainer2() {
		String text = container2.findElement(By.cssSelector("div[class='ion-flat-service-content-text']")).getText();
		String testimony = container2.findElement(By.cssSelector("div[class='ion-flat-service-testimony-person']")).getText();
		
		Assert.assertEquals(UIPageConstants.CONTAINER2_TEXT, text);
		Assert.assertEquals(UIPageConstants.CONTAINER2_TESTIMONY, testimony);
	}
	
	public void verifyContainer3() {
		WebElement textEle = container3.findElement(By.cssSelector("div[class='HomepageModalToggle']")).findElement(By.tagName("p"));
		
		click(textEle);
		Assert.assertTrue(policyPopUp.isDisplayed());
		Assert.assertEquals(UIPageConstants.POLICY_HEADING.toUpperCase(), CommonUtils.removeLineBreaks(policyHeading.getText()));
		Assert.assertEquals(UIPageConstants.POLICY_MESSAGE, policyMessage.getText());
		Assert.assertEquals(UIPageConstants.POLICY_CHOOSE.toUpperCase(), policyChoose.getText());
		Assert.assertEquals(UIPageConstants.POLICY_OPTION_1, CommonUtils.removeLineBreaks(policyOption1.getText()));
		Assert.assertEquals(UIPageConstants.POLICY_OPTION_2, policyOption2.getText());
		
		click(policyPopUpClose);
		Assert.assertFalse(policyPopUp.isDisplayed());
		
	}
	
	
	public void verifyContainer4() {
		String text1 = container4.findElement(By.cssSelector("div[class='ion-flat-fast ion-flat-bigheading']")).getText();
		String text2 = container4.findElement(By.cssSelector("div[class='ion-flat-affordable ion-flat-bigheading']")).getText();
		String text3 = container4.findElement(By.cssSelector("div[class='ion-flat-dependable ion-flat-bigheading']")).getText();
//		String text4 = container4.findElement(By.cssSelector("div[class='ion-flat-time']")).getText();
	
		Assert.assertEquals(UIPageConstants.CONTAINER4_TEXT1, text1);
		Assert.assertEquals(UIPageConstants.CONTAINER4_TEXT2, text2);
		Assert.assertEquals(UIPageConstants.CONTAINER4_TEXT3, text3);
		//Assert.assertEquals(UIPageConstants.CONTAINER4_TEXT4, text4);
		
	}
}


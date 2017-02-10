package com.ins.utils;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.ins.logger.LogUtil;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;

/**
 * This class handles the Webdriver actions and events
 * 
 *
 */
public class WebUtil {


	/**
	 * Puts the driver to default sleep which is 1000 milliseconds
	 */
	public static void waitForPageLoaded() {
		waitForPageLoaded(1000);
	}

	/**
	 * Puts the driver to sleep for the given time in milliseconds
	 */
	public static void waitForPageLoaded(long sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {
			LogUtil.logError("Timeout waiting for Page Load Request to complete.");
			Assert.fail("[ERROR]:Timeout waiting for Page Load Request to complete.");
		}
	}


	public static void scrollDown(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		waitForPageLoaded(2000);
	}

	public static void scrollTillBottom(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(
				"window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
		waitForPageLoaded(2000);
	}

	public static void scrollUp(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-250)", "");
	}

	public static void scrollToElement(WebDriver driver, WebElement element) {
		final JavascriptLibrary javascript = new JavascriptLibrary();

		javascript.executeScript(driver, "arguments[0].scrollIntoView(true);", element);
		waitForPageLoaded(200);
	}

	public static void selectOptionByIndex(int index, WebElement ele) {
		List<WebElement> allOptions = getAllOptions(ele);
		if (allOptions.size() > index) {
			Select select = new Select(ele);
			select.selectByIndex(index);
		}
	}

	public static void selectOptionByText(String text, WebElement ele) {
		Select select = new Select(ele);
		select.selectByVisibleText(text);
	}
	
	public static void selectOptionByValue(String text, WebElement ele) {
		Select select = new Select(ele);
		select.selectByValue(text);
	}

	public static List<WebElement> getAllOptions(WebElement ele) {
		Select select = new Select(ele);
		return select.getOptions();
	}
	
	
	/**
	 * This method handles the alert window in firefox and chrome
	 * @param ele
	 * @param driver
	 */
	public static void clickElement(WebElement ele, WebDriver driver) {
		try {
			waitForPageLoaded();
			ele.click();
			
		} catch(WebDriverException e) {
			Actions actions = new Actions(driver);
			actions.moveToElement(ele).click().perform();
		}
		
	}
	
	public static boolean isElementPresent(WebElement ele) {
		boolean elePresent = true;
		
		if(ele != null) {
			try {
				ele.getText();
			} catch (StaleElementReferenceException se) {
				elePresent = false;
			} catch (NoSuchElementException e) {
				elePresent = false;
			}	
		} else {
			elePresent = false;
		}
		
		return elePresent;
	}


	public static void click(WebElement ele, WebDriver driver) {
		clickElement(ele, driver);
		WebUtil.waitForPageLoaded();
	}

	public static void scrollToEleAndClick(WebElement ele, WebDriver driver) {
		scrollToElement(driver, ele);
		clickElement(ele, driver);
		WebUtil.waitForPageLoaded(1000);
	}


	public static String getSelectedText(WebElement ele) {
		Select select = new Select(ele);
		return select.getFirstSelectedOption().getText();
	}


}

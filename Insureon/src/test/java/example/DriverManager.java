package example;

import org.openqa.selenium.WebDriver;

import com.ins.utils.WebUtil;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import support.Browser;

/**
 * This class takes care of the start and end process of a test
 * @Before: Will include steps to perform before the test starts
 * @After: Will include the actions to perform when a test is complete
 *
 */
public class DriverManager {
	
	public static WebDriver globalDriver;
	
	
    @Before
	public void start(Scenario scenario) {
    	globalDriver = Browser.launch();
	}
	
	
	
    @After
	public void end(Scenario scenario) {
		// Close Browser
		if(globalDriver != null) {
			WebUtil.waitForPageLoaded(2000);
			globalDriver.quit();
		}
	}
}

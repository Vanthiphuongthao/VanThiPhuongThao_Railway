package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Constant.Constant;
import Common.Utilities;

public class BaseTest {
	UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

	// Not-active Account Existing
	UserAccount notActUserAccount = new UserAccount(Constant.notACTVEMAIL, Constant.PASSWORD, Constant.PID);

	Utilities utilities = new Utilities();

	@BeforeMethod
	public void beforeMethod() {
		Constant.WEBDRIVER = new ChromeDriver();
		Constant.WEBDRIVER.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() {
		Constant.WEBDRIVER.quit();
	}

}

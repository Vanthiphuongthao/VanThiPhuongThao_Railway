package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Constant.Constant;
import Common.Utilities;
import Enums.Message;

public class BaseTest {

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

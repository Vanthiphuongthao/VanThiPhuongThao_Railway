package Railway;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Constant.Constant;

public class BaseTest {
	@Parameters({ "browser" })
	@BeforeMethod
	public void beforeMethod(@Optional("chrome") String myBrowser) {
		if (myBrowser.equals("chrome")) {
			Constant.WEBDRIVER = new ChromeDriver();

		} else if (myBrowser.equals("firefox")) {
			Constant.WEBDRIVER = new FirefoxDriver();

		}
		Constant.WEBDRIVER.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() {
		Constant.WEBDRIVER.quit();
	}

}

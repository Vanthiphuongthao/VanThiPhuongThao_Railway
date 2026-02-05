package GuerrillaMail;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import Common.Utilities;
import Constant.Constant;
import Railway.HomePage;
import Railway.RegisterPage;
import Railway.UserAccount;

public class testMail {

	public static void main(String[] args) {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
		Constant.WEBDRIVER = new ChromeDriver();
		Constant.WEBDRIVER.manage().window().maximize();
		// open
		GuerrillaMailPage guerrillaMailPage = new GuerrillaMailPage();
		guerrillaMailPage.open();

		// set variable for fake mail
		String tailString = "@sharklasers.com";
		String emailNotActive = Utilities.generateRandomString(8);

		guerrillaMailPage.createFakeMail(emailNotActive);

		String guerrillaWindow = Constant.WEBDRIVER.getWindowHandle();

		// open railway in new tab
		((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.open(arguments[0], '_blank');",
				Constant.RAILWAY_URL);
		
		// switch to railway tab
		for (String window : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(window);
			if (Constant.WEBDRIVER.getCurrentUrl().contains("railway")) {
				break;
			}
		}

		// 
		HomePage homePage = new HomePage();
		homePage.open();

		RegisterPage registerPage = homePage.gotoRegisterPage();
		
		userAccount.setEmail(emailNotActive.concat(tailString));
		registerPage.register(userAccount);
		
		
		// switch to fake mail tab
		Constant.WEBDRIVER.switchTo().window(guerrillaWindow);
		guerrillaMailPage.closeAdIfPresent();
	    guerrillaMailPage.activeNewAccount();
	    
	    
	    

	}
}

package Railway;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import Common.Utilities;
import Constant.Constant;
import GuerrillaMail.GuerrillaMailPage;
import Enums.Tabs;

public class AccountFlow {
	private String switchToRailwayTab() {
		for (String window : Constant.WEBDRIVER.getWindowHandles()) {
			Constant.WEBDRIVER.switchTo().window(window);
			if (Constant.WEBDRIVER.getCurrentUrl().contains("railway")) {
				return window;
			}
		}
		return null;
	}
	
	public void switchToWindow(String windowHandle) {
	    Constant.WEBDRIVER.switchTo().window(windowHandle);
	}

	public UserAccount registerAndNotActive(UserAccount userAccount) {

		// 1. open Guerrilla Mail
		GuerrillaMailPage guerrillaMailPage = new GuerrillaMailPage();
		guerrillaMailPage.open();

		// 2. generate fake email
		String emailPrefix = Utilities.generateRandomString(8);
		String email = emailPrefix + "@sharklasers.com";

		guerrillaMailPage.createFakeMail(emailPrefix);
		String guerrillaWindow = Constant.WEBDRIVER.getWindowHandle();

		// 3. open Railway in new tab
		((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.open(arguments[0], '_blank');",
				Constant.RAILWAY_URL);

		String railwayWindow = switchToRailwayTab();

		// 4. register account
		HomePage homePage = new HomePage();
		RegisterPage registerPage = homePage.gotoPage(Tabs.REGISTER, RegisterPage.class);

		userAccount.setEmail(email);
		registerPage.register(userAccount, HomePage.class);

		return userAccount;
	}

	public UserAccount registerAndActiveAccount(UserAccount userAccount) {

		// 1. open Guerrilla Mail
		GuerrillaMailPage guerrillaMailPage = new GuerrillaMailPage();
		guerrillaMailPage.open();

		// 2. generate fake email
		String emailPrefix = Utilities.generateRandomString(8);
		String email = emailPrefix + "@sharklasers.com";
		userAccount.setEmail(email);

		guerrillaMailPage.createFakeMail(emailPrefix);
		String guerrillaWindow = Constant.WEBDRIVER.getWindowHandle();

		// 3. open Railway in new tab
		((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.open(arguments[0], '_blank');",
				Constant.RAILWAY_URL);

		String railwayWindow = switchToRailwayTab();

		// 4. register account
		HomePage homePage = new HomePage();
		RegisterPage registerPage = homePage.gotoPage(Tabs.REGISTER, RegisterPage.class);

		registerPage.register(userAccount, RegisterPage.class);

		// 5. activate via email
		Constant.WEBDRIVER.switchTo().window(guerrillaWindow);
		guerrillaMailPage.activateAccountByEmail("thanhletraining03@gmail.com");

		// 6. switch back to railway
		Constant.WEBDRIVER.switchTo().window(railwayWindow);

		return userAccount;
	}

}

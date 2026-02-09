package Railway;

import Common.Utilities;
import Constant.Constant;
import GuerrillaMail.GuerrillaMailPage;
import Enums.Email;
import Enums.Tabs;

public class Business {

	public UserAccount registerNotActiveAccount(UserAccount userAccount) {

		// 1. set email
//		String mainTab = "Safe Railway";

		String emailPrefix = Utilities.generateRandomString(8);
		String emailDomain = Email.EMAIL_DOMAIN_SHARKLASERS.getEmail();
		String email = emailPrefix.concat(emailDomain);
		userAccount.setEmail(email);

		// 2. open railway & register
		HomePage homePage = new HomePage();
		homePage.open();

		RegisterPage registerPage = homePage.gotoPage(Tabs.REGISTER, RegisterPage.class);
		registerPage.register(userAccount, HomePage.class);

		return userAccount;
	}

	public UserAccount registerAccount(UserAccount userAccount) {

		// 1. register but not active
		registerNotActiveAccount(userAccount);

		// 2. open G Mail in new tab
		Utilities.openUrlInNewTab(Constant.GUERRILLAMAIL_URL);
		GuerrillaMailPage mailPage = new GuerrillaMailPage();

		// 3. set mail
		String emailPrefix = userAccount.getEmail().split("@")[0];
		mailPage.setMail(emailPrefix);

		// 4. activate account by email
		mailPage.activateAccountByEmail(Email.ACTIVE_ACCOUNT_EMAIL_SUBJECT.getEmail());

		return userAccount;
	}

//	private String switchToRailwayTab() {
//		for (String window : Constant.WEBDRIVER.getWindowHandles()) {
//			Constant.WEBDRIVER.switchTo().window(window);
//			if (Constant.WEBDRIVER.getCurrentUrl().contains("railway")) {
//				return window;
//			}
//		}
//		return null;
//	}

//	public void switchToWindow(String windowHandle) {
//		Constant.WEBDRIVER.switchTo().window(windowHandle);
//	}

//	public UserAccount registerAndNotActive(UserAccount userAccount) {
//		// 1. open Guerrilla Mail
//		GuerrillaMailPage guerrillaMailPage = new GuerrillaMailPage();
//		guerrillaMailPage.open();
//
//		// 2. generate fake email
//		String emailPrefix = Utilities.generateRandomString(8);
//		String email = emailPrefix + "@sharklasers.com";
//
//		guerrillaMailPage.setMail(emailPrefix);
//		String guerrillaWindow = Constant.WEBDRIVER.getWindowHandle();
//
//		// 3. open Railway in new tab
//		((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.open(arguments[0], '_blank');",
//				Constant.RAILWAY_URL);
//
//		String railwayWindow = switchToRailwayTab();
//
//		// 4. register account
//		HomePage homePage = new HomePage();
//		RegisterPage registerPage = homePage.gotoPage(Tabs.REGISTER, RegisterPage.class);
//
//		userAccount.setEmail(email);
//		registerPage.register(userAccount, HomePage.class);
//
//		return userAccount;
//	}

//	public UserAccount registerAndActiveAccount(UserAccount userAccount) {
//
//		// 1. open Guerrilla Mail
//		GuerrillaMailPage guerrillaMailPage = new GuerrillaMailPage();
//		guerrillaMailPage.open();
//
//		// 2. generate fake email
//		String emailPrefix = Utilities.generateRandomString(8);
//		String email = emailPrefix + "@sharklasers.com";
//		userAccount.setEmail(email);
//
//		guerrillaMailPage.setMail(emailPrefix);
//		String guerrillaWindow = Constant.WEBDRIVER.getWindowHandle();
//
//		// 3. open Railway in new tab
//		((JavascriptExecutor) Constant.WEBDRIVER).executeScript("window.open(arguments[0], '_blank');",
//				Constant.RAILWAY_URL);
//
//		String railwayWindow = switchToRailwayTab();
//
//		// 4. register account
//		HomePage homePage = new HomePage();
//		RegisterPage registerPage = homePage.gotoPage(Tabs.REGISTER, RegisterPage.class);
//
//		registerPage.register(userAccount, RegisterPage.class);
//
//		// 5. activate via email
//		Constant.WEBDRIVER.switchTo().window(guerrillaWindow);
//		guerrillaMailPage.activateAccountByEmail("thanhletraining03@gmail.com");
//
//		// 6. switch back to railway
//		Constant.WEBDRIVER.switchTo().window(railwayWindow);
//
//		return userAccount;
//	}

}

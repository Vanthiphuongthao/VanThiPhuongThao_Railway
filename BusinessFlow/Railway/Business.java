package Railway;

import Common.Utilities;
import Constant.Constant;
import Enums.Email;
import Enums.Tabs;
import GuerrillaMail.GuerrillaMailPage;

public class Business {

	public static UserAccount registerNotActiveAccount(UserAccount userAccount) {

		// 1. set email
		String emailPrefix = Utilities.generateRandomString(8);
		String emailDomain = Email.EMAIL_DOMAIN_SHARKLASERS.setEmail();
		String email = emailPrefix.concat(emailDomain);
		userAccount.setEmail(email);

		// 2. open railway & register
		HomePage homePage = new HomePage();
		homePage.open();

		RegisterPage registerPage = homePage.gotoPage(Tabs.REGISTER, RegisterPage.class);
		registerPage.register(userAccount, HomePage.class);

		return userAccount;
	}

	public static UserAccount registerAccount(UserAccount userAccount, String mainTab) {

		// 1. register but not active
		registerNotActiveAccount(userAccount);
//		String mainHandle = Constant.WEBDRIVER.getWindowHandle();

		// 2. open G Mail in new tab
		Utilities.openUrlInNewTab(Constant.GUERRILLAMAIL_URL);
		GuerrillaMailPage mailPage = new GuerrillaMailPage();

		// 3. activate account by email
		String emailPrefix = userAccount.getEmail().split("@")[0];
		String emailSubject = Email.ACTIVE_ACCOUNT_EMAIL_SUBJECT.setEmail();
		mailPage.setMail(emailPrefix);
		mailPage.activateAccount(emailSubject);
		Utilities.closeAllTabExceptHandle(mainTab);

		return userAccount;
	}

}

package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Enums.Tabs;

public class LogoutTest extends BaseTest {

	@Test
	public void TC06() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		System.out.println("TestCase06 - User is redirected to Home page after logging out");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Login with valid Email and Password");
		LoginPage loginPage = homePage.gotoPage(Tabs.LOGIN, LoginPage.class);
		loginPage.login(userAccount, LoginPage.class);

		System.out.println("3. Click on \"FAQ\" tab");
		homePage.gotoPage(Tabs.FAQ, FAQPage.class);

		System.out.println("4. Click on \"Log out\" tab");
		homePage.gotoPage(Tabs.LOGOUT, HomePage.class);

		System.out.println("VP: Home page displays");
		String actualTabName = homePage.getSelectedTabName();
		String expectedTabName = Tabs.HOME.getTab();
		Assert.assertEquals(actualTabName, expectedTabName, "Home page is not displays as expected");
		
		System.out.println("VP: \"Log out\" tab is disappeared");
		Assert.assertFalse(homePage.isTabExist(Tabs.LOGOUT), "Log out tab is not disappeared as expected");
	}

}

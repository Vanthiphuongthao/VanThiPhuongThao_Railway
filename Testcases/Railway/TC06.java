package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;


public class TC06 extends BaseTest {
	@Test
	public void TestCase06() {
		System.out.println("TC06 - User is redirected to Home page after logging out");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Login with valid Email and Password");
		LoginPage loginPage = homePage.gotoLoginPage();
		loginPage.login(userAccount);

		System.out.println("3. Click on \"FAQ\" tab");
		FAQPage faqPage = homePage.gotoFAQPage();

		System.out.println("4. Click on \"Log out\" tab");
		homePage = faqPage.gotoLogoutPage();

		String actualTabName = homePage.getSelectedTabName();
		String expectedTabNameString = "Home";

		// Verify Home page displays
		Assert.assertEquals(actualTabName, expectedTabNameString, "Home page displays");

		
		// Verify "Log out" tab is disappeared
		String tabName = "Logout";
//		Boolean actualResult = homePage.isTabExist(tabName);
//		Boolean expectedResult = false;
//		Assert.assertEquals(actualResult, expectedResult, "Log out tab is disappeared");
		Assert.assertFalse(homePage.isTabExist(tabName), "");
		
//		Assert.assertFalse(GeneralPage.isTabExist(tabName), "Log out tab is disappeared");
	}

}

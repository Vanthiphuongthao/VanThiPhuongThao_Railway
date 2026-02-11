package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Enums.SeatType;
import Enums.Station;
import Enums.Tabs;

public class CancelBookingTest extends BaseTest {
	@Test
	public void TC16() {
		String mainTab = "Safe Railway";

		int departDate = 2;
		String departFrom = Station.NHA_TRANG.setStation();
		String arriveAt = Station.HUE.setStation();
		String seatType = SeatType.SOFT_BED_AC.getSeatType();
		String amountTicket = "1";

		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
		TicketInfo ticket = new TicketInfo(departDate, departFrom, arriveAt, seatType, amountTicket);

		System.out.println("TestCase16 - User can cancel a ticket");

		System.out.println("Pre-condition: an actived account is existing");
		Business.registerAccount(userAccount);
		Utilities.closeAllTabExceptHandle(mainTab);

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(Tabs.LOGIN, LoginPage.class);
		loginPage.login(userAccount, HomePage.class);

		System.out.println("3. Book a ticket");
		BookTicketPage bookTicketPage = homePage.gotoPage(Tabs.BOOK_TICKET, BookTicketPage.class);
		bookTicketPage.bookTicket(ticket);

		System.out.println("4. Click on \"My ticket\" tab");
		MyTicketPage myTicketPage = bookTicketPage.gotoPage(Tabs.MY_TICKET, MyTicketPage.class);

		System.out.println("5. Click on \"Cancel\" button of ticket which user want to cancel.");
		myTicketPage.clickCancelTicket(departFrom, arriveAt);

		System.out.println("6. Click on \"OK\" button on Confirmation message \"Are you sure?\"");
		myTicketPage.acceptCancelAlert();

		System.out.println("VP: The canceled ticket is disappeared");
		boolean isDisplayed = myTicketPage.isTicketDisplayed(departFrom, arriveAt);

		Assert.assertFalse(isDisplayed, "Ticket Cancel Fail");
	}

}

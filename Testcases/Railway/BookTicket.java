package Railway;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Enums.Message;
import Enums.SeatType;
import Enums.Station;
import Enums.Tabs;
import Enums.TicketHeader;

public class BookTicket extends BaseTest {
	@Test
	public void TC12() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
		
		int targetDate = 2;
		String departFrom = Station.NHA_TRANG.getStation();
		String arriveAt = Station.HUE.getStation();
		String seatType = SeatType.SOFT_BED_AC.getSeatType();
		String amountTicket = "1";		

		System.out.println("TestCase12 - User can't create account with an already in-use email");
		System.out.println("Pre-condition: an actived account is existing");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(Tabs.LOGIN, LoginPage.class);
		loginPage.login(userAccount, HomePage.class);
		
		System.out.println("3. Click on \"Book ticket\" tab");
		System.out.println("4. Select the next 2 days from \"Depart date\"");
		System.out.println("5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
		System.out.println("6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
		System.out.println("7. Select \"1\" for \"Ticket amount\"");
		System.out.println("8. Click on \"Book ticket\" button");
		
		BookTicketPage bookTicketPage = homePage.gotoPage(Tabs.BOOK_TICKET, BookTicketPage.class);
		
		bookTicketPage.bookTicket(targetDate, departFrom, arriveAt, seatType, amountTicket);
		
		// verify book ticket message
		String actualSuccessMsg = bookTicketPage.getBookTicketSuccessMsgText();
		String expectedSuccessMsg = Message.BOOK_TICKET_SUCCESS_MSG.getMessage();
		String errorMsg = Message.BOOK_TICKET_MSG_NOT_DISPLAYED.getMessage();
		
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, errorMsg);
		
		// verify depart station
		
		Map<String, String> ticketInfo = bookTicketPage.getTicketInfo(2);
		
		Assert.assertEquals(ticketInfo.get(TicketHeader.DEPART_STATION.getHeaderText()), departFrom);
		Assert.assertEquals(ticketInfo.get(TicketHeader.ARRIVE_STATION.getHeaderText()), arriveAt);
		Assert.assertEquals(ticketInfo.get(TicketHeader.SEAT_TYPE.getHeaderText()), seatType);
		Assert.assertEquals(ticketInfo.get(TicketHeader.AMOUNT.getHeaderText()), amountTicket);
	}
	
	@Test
	public void TC13() {
		
		System.out.println("TestCase13 - User can book many tickets at a time");
		System.out.println("Pre-condition: an actived account is existing");
		
	}

}

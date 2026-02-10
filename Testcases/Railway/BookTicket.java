package Railway;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Enums.Message;
import Enums.SeatPrice;
import Enums.SeatType;
import Enums.Station;
import Enums.Tabs;
import Enums.TicketHeader;
import Enums.Title;

public class BookTicket extends BaseTest {
	@Test
	public void TC12() {
		int targetDate = 2;
		String departFrom = Station.NHA_TRANG.getStation();
		String arriveAt = Station.HUE.getStation();
		String seatType = SeatType.SOFT_BED_AC.getSeatType();
		String amountTicket = "1";

		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
		TicketInfo ticket = new TicketInfo(targetDate, departFrom, arriveAt, seatType, amountTicket);

		System.out.println("TestCase12 - User can book 1 ticket at a time");
		System.out.println("Pre-condition: an actived account is existing");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(Tabs.LOGIN, LoginPage.class);
		loginPage.login(userAccount, HomePage.class);

		System.out.println("3. Click on \"Book ticket\" tab");
		BookTicketPage bookTicketPage = homePage.gotoPage(Tabs.BOOK_TICKET, BookTicketPage.class);

		System.out.println("4. Select the next 2 days from \"Depart date\"");
		System.out.println("5. Select Depart from \"Nha Trang\" and Arrive at \"Huế\"");
		System.out.println("6. Select \"Soft bed with air conditioner\" for \"Seat type\"");
		System.out.println("7. Select \"1\" for \"Ticket amount\"");
		System.out.println("8. Click on \"Book ticket\" button");

		bookTicketPage.bookTicket(ticket);

		// verify book ticket message
		// println ra verify
		String actualSuccessMsg = bookTicketPage.getBookTicketSuccessMsgText();
		String expectedSuccessMsg = Message.BOOK_TICKET_SUCCESS_MSG.getMessage();
		String errorMsg = Message.BOOK_TICKET_MSG_NOT_DISPLAYED.getMessage();

		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, errorMsg);

		// verify Ticket information

		Map<TicketHeader, String> ticketInfo = bookTicketPage.getTicketInfo();
		
		// assert MSg
		Assert.assertEquals(ticketInfo.get(TicketHeader.DEPART_STATION), departFrom);
		Assert.assertEquals(ticketInfo.get(TicketHeader.ARRIVE_STATION), arriveAt);
		Assert.assertEquals(ticketInfo.get(TicketHeader.SEAT_TYPE), seatType);
		Assert.assertEquals(ticketInfo.get(TicketHeader.AMOUNT), amountTicket);
	}

	@Test
	public void TC13() {
		int targetDate = 25;
		String departFrom = Station.NHA_TRANG.getStation();
		String arriveAt = Station.SAI_GON.getStation();
		String seatType = SeatType.SOFT_SEAT_AC.getSeatType();
		String amountTicket = "5";
		// int amount

		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
		TicketInfo ticket = new TicketInfo(targetDate, departFrom, arriveAt, seatType, amountTicket);

		System.out.println("TestCase13 - User can book many tickets at a time");
		System.out.println("Pre-condition: an actived account is existing");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(Tabs.LOGIN, LoginPage.class);
		loginPage.login(userAccount, HomePage.class);

		System.out.println("3. Click on \"Book ticket\" tab");
		BookTicketPage bookTicketPage = homePage.gotoPage(Tabs.BOOK_TICKET, BookTicketPage.class);

		System.out.println("4. Select the next 25 days from \"Depart date\"");
		System.out.println("5. Select \"Nha Trang\" for \"Depart from\" and \"Sài Gòn\" for \"Arrive at\"");
		System.out.println("6. Select \"Soft seat with air conditioner\" for \"Seat type\"");
		System.out.println("7. Select \"5\" for \"Ticket amount\"");
		System.out.println("8. Click on \"Book ticket\" button");

		bookTicketPage.bookTicket(ticket);

		// verify book ticket message
		String actualSuccessMsg = bookTicketPage.getBookTicketSuccessMsgText();
		String expectedSuccessMsg = Message.BOOK_TICKET_SUCCESS_MSG.getMessage();
		String errorMsg = Message.BOOK_TICKET_MSG_NOT_DISPLAYED.getMessage();

		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, errorMsg);

		// verify Ticket information

		Map<TicketHeader, String> ticketInfo = bookTicketPage.getTicketInfo();

		Assert.assertEquals(ticketInfo.get(TicketHeader.DEPART_STATION), departFrom);
		Assert.assertEquals(ticketInfo.get(TicketHeader.ARRIVE_STATION), arriveAt);
		Assert.assertEquals(ticketInfo.get(TicketHeader.SEAT_TYPE), seatType);
		Assert.assertEquals(ticketInfo.get(TicketHeader.AMOUNT), amountTicket);
	}

	@Test
	public void TC14() {
		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		String depart = Station.DA_NANG.getStation();
		String arrive = Station.SAI_GON.getStation();

		System.out.println("TestCase14 - User can check price of ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(Tabs.LOGIN, LoginPage.class);
		loginPage.login(userAccount, HomePage.class);

		System.out.println("3. Click on \"Timetable\" tab");
		TimetablePage timetablePage = homePage.gotoPage(Tabs.TIMETABLE, TimetablePage.class);

		System.out.println("4. Click on \"check price\" link of the route from \"Đà Nẵng\" to \"Sài Gòn\"");
		TicketPricePage ticketPricePage = timetablePage.clickCheckPrice(depart, arrive);

		// Verify Home page displays
		String actualTabName = ticketPricePage.getSelectedTabName();
		String expectedTabName = Tabs.TICKET_PRICE.getText();
		String assertMsg = Message.TAB_NAME_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualTabName, expectedTabName, assertMsg);

		// Verify Ticket Price title
		String actualTitle = ticketPricePage.getPriceTitle(depart, arrive);
		String expectedTitle = Title.TICKET_PRICE_DN_SG.getTitleTicketPrice();
		String assertTitleMsg = Message.TITLE_NOT_DISPLAYED.getMessage();
		Assert.assertEquals(actualTitle, expectedTitle, assertTitleMsg);

		// Verify price for each seat type
		String HS_actualPrice = ticketPricePage.getPriceBySeat(SeatPrice.HS.getCode());
		String SS_actualPrice = ticketPricePage.getPriceBySeat(SeatPrice.SS.getCode());
		String SSC_actualPrice = ticketPricePage.getPriceBySeat(SeatPrice.SSC.getCode());
		String HB_actualPrice = ticketPricePage.getPriceBySeat(SeatPrice.HB.getCode());
		String SB_actualPrice = ticketPricePage.getPriceBySeat(SeatPrice.SB.getCode());
		String SBC_actualPrice = ticketPricePage.getPriceBySeat(SeatPrice.SBC.getCode());

		String HS_expectedPrice = SeatPrice.HS.getExpectedPrice();
		String SS_expectedPrice = SeatPrice.SS.getExpectedPrice();
		String SSC_expectedPrice = SeatPrice.SSC.getExpectedPrice();
		String HB_expectedPrice = SeatPrice.HB.getExpectedPrice();
		String SB_expectedPrice = SeatPrice.SB.getExpectedPrice();
		String SBC_expectedPrice = SeatPrice.SBC.getExpectedPrice();

		Assert.assertEquals(HS_actualPrice, HS_expectedPrice);
		Assert.assertEquals(SS_actualPrice, SS_expectedPrice);
		Assert.assertEquals(SSC_actualPrice, SSC_expectedPrice);
		Assert.assertEquals(HB_actualPrice, HB_expectedPrice);
		Assert.assertEquals(SB_actualPrice, SB_expectedPrice);
		Assert.assertEquals(SBC_actualPrice, SBC_expectedPrice);
	}

	@Test
	public void TC15() {
		int targetDate = 1;
		String departFrom = Station.QUANG_NGAI.getStation();
		String arriveAt = Station.HUE.getStation();
		String seatType = SeatType.SOFT_SEAT_AC.getSeatType();
		String amountTicket = "5";

		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
		TicketInfo ticket = new TicketInfo(targetDate, departFrom, arriveAt, seatType, amountTicket);

		System.out.println("TestCase15 - User can book ticket from Timetable");
		System.out.println("Pre-condition: an actived account is existing");

		System.out.println("1. Navigate to QA Railway Website");
		HomePage homePage = new HomePage();
		homePage.open();

		System.out.println("2. Login with a valid account");
		LoginPage loginPage = homePage.gotoPage(Tabs.LOGIN, LoginPage.class);
		loginPage.login(userAccount, HomePage.class);

		System.out.println("3. Click on \"Timetable\" tab");
		BookTicketPage bookTicketPage = homePage.gotoPage(Tabs.BOOK_TICKET, BookTicketPage.class);

		System.out.println("4. Click on book ticket of route \"Quảng Ngãi\" to \"Huế\"");
		System.out.println("5. Select Depart date = tomorrow");
		System.out.println("6. Select Ticket amount = 5");
		System.out.println("7. Click on \"Book ticket\" button");

		bookTicketPage.bookTicket(ticket);

		// verify book ticket message
		String actualSuccessMsg = bookTicketPage.getBookTicketSuccessMsgText();
		String expectedSuccessMsg = Message.BOOK_TICKET_SUCCESS_MSG.getMessage();
		String errorMsg = Message.BOOK_TICKET_MSG_NOT_DISPLAYED.getMessage();

		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, errorMsg);

		// verify Ticket information

		Map<TicketHeader, String> ticketInfo = bookTicketPage.getTicketInfo();

		Assert.assertEquals(ticketInfo.get(TicketHeader.DEPART_STATION), departFrom);
		Assert.assertEquals(ticketInfo.get(TicketHeader.ARRIVE_STATION), arriveAt);
		Assert.assertEquals(ticketInfo.get(TicketHeader.SEAT_TYPE), seatType);
		Assert.assertEquals(ticketInfo.get(TicketHeader.AMOUNT), amountTicket);
	}

}

package Railway;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import Enums.Message;
import Enums.SeatPrice;
import Enums.SeatType;
import Enums.Station;
import Enums.Tabs;
import Enums.TicketHeader;
import Enums.Title;

public class BookTicketTest extends BaseTest {
	@Test
	public void TC12() {
		String mainTab = "Safe Railway";

		String departDate = "2";
		String departFrom = Station.NHA_TRANG.setStation();
		String arriveAt = Station.HUE.setStation();
		String seatType = SeatType.SOFT_BED_AC.getSeatType();
		Integer amountTicket = 1;

		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
		TicketInfo ticket = new TicketInfo(departDate, departFrom, arriveAt, seatType, amountTicket);

		System.out.println("TestCase12 - User can book 1 ticket at a time");

		System.out.println("Pre-condition: an actived account is existing");
		Business.registerAccount(userAccount, mainTab);

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
		bookTicketPage.bookTicketAfterDaysFromDefault(ticket);

		System.out.println("VP: Message \"Ticket booked successfully!\" displays");
		String actualSuccessMsg = bookTicketPage.getBookTicketSuccessMsgText();
		String expectedSuccessMsg = Message.BOOK_TICKET_SUCCESS_MSG.getMessage();
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Book ticket message is not displayed as expected");

		System.out.println(
				"VP: Ticket information display correctly (Depart Date, Depart Station, Arrive Station, Seat Type, Amount)");
		Map<TicketHeader, String> ticketInfo = bookTicketPage.getTicketInfo();

		Assert.assertEquals(ticketInfo.get(TicketHeader.DEPART_STATION), departFrom,
				"Depart Station is not displayed as expected");
		Assert.assertEquals(ticketInfo.get(TicketHeader.ARRIVE_STATION), arriveAt,
				"Arrive Station is not displayed as expected");
		Assert.assertEquals(ticketInfo.get(TicketHeader.SEAT_TYPE), seatType, "Seat Type is not displayed as expected");
		Assert.assertEquals(ticketInfo.get(TicketHeader.AMOUNT), String.valueOf(amountTicket),
				"Amount Ticket is not displayed as expected");
	}

	@Test
	public void TC13() {
		String mainTab = "Safe Railway";

		String departDate = "25";
		String departFrom = Station.NHA_TRANG.setStation();
		String arriveAt = Station.SAI_GON.setStation();
		String seatType = SeatType.SOFT_SEAT_AC.getSeatType();
		Integer amountTicket = 5;

		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
		TicketInfo ticket = new TicketInfo(departDate, departFrom, arriveAt, seatType, amountTicket);

		System.out.println("TestCase13 - User can book many tickets at a time");

		System.out.println("Pre-condition: an actived account is existing");
		Business.registerAccount(userAccount, mainTab);

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

		bookTicketPage.bookTicketAfterDaysFromDefault(ticket);

		System.out.println("VP: Message \"Ticket booked successfully!\" displays");
		String actualSuccessMsg = bookTicketPage.getBookTicketSuccessMsgText();
		String expectedSuccessMsg = Message.BOOK_TICKET_SUCCESS_MSG.getMessage();
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Book ticket message is not displayed as expected");

		System.out.println(
				"VP: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		Map<TicketHeader, String> ticketInfo = bookTicketPage.getTicketInfo();

		Assert.assertEquals(ticketInfo.get(TicketHeader.DEPART_STATION), departFrom,
				"Depart Station is not displayed as expected");
		Assert.assertEquals(ticketInfo.get(TicketHeader.ARRIVE_STATION), arriveAt,
				"Arrive Station is not displayed as expected");
		Assert.assertEquals(ticketInfo.get(TicketHeader.SEAT_TYPE), seatType, "Seat Type is not displayed as expected");
		Assert.assertEquals(ticketInfo.get(TicketHeader.AMOUNT), String.valueOf(amountTicket),
				"Amount Ticket is not displayed as expected");
	}

	@Test
	public void TC14() {
		String mainTab = "Safe Railway";

		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);

		String depart = Station.DA_NANG.setStation();
		String arrive = Station.SAI_GON.setStation();

		System.out.println("TestCase14 - User can check price of ticket from Timetable");

		System.out.println("Pre-condition: an actived account is existing");
		Business.registerAccount(userAccount, mainTab);

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

		System.out.println("VP: \"Ticket Price\" page is loaded");
		String actualTabName = ticketPricePage.getSelectedTabName();
		String expectedTabName = Tabs.TICKET_PRICE.getTab();
		Assert.assertEquals(actualTabName, expectedTabName, "Tab name is not displayed as expected");

		System.out.println("VP: Ticket table shows \"Ticket price from Đà Nẵng to Sài Gòn\"");
		String actualTitle = ticketPricePage.getPriceTitle(depart, arrive);
		String expectedTitle = Title.TICKET_PRICE_DN_SG.getTitleTicketPrice();
		Assert.assertEquals(actualTitle, expectedTitle, "Title is not displayed as expected");

		System.out.println("VP: Price for each seat displays correctly");
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

		Assert.assertEquals(HS_actualPrice, HS_expectedPrice, "Price for HS seat is not displayed as expected");
		Assert.assertEquals(SS_actualPrice, SS_expectedPrice, "Price for SS seat is not displayed as expected");
		Assert.assertEquals(SSC_actualPrice, SSC_expectedPrice, "Price for SSC seat is not displayed as expected");
		Assert.assertEquals(HB_actualPrice, HB_expectedPrice, "Price for HB seat is not displayed as expected");
		Assert.assertEquals(SB_actualPrice, SB_expectedPrice, "Price for SB seat is not displayed as expected");
		Assert.assertEquals(SBC_actualPrice, SBC_expectedPrice, "Price for SBC seat is not displayed as expected");
	}

	@Test
	public void TC15() {
		String mainTab = "Safe Railway";

		String departDate = "1";
		String departFrom = Station.QUANG_NGAI.setStation();
		String arriveAt = Station.HUE.setStation();
		String seatType = SeatType.SOFT_SEAT_AC.getSeatType();
		Integer amountTicket = 5;

		UserAccount userAccount = new UserAccount(Constant.USERNAME, Constant.PASSWORD, Constant.PID);
		TicketInfo ticket = new TicketInfo(departDate, departFrom, arriveAt, seatType, amountTicket);

		System.out.println("TestCase15 - User can book ticket from Timetable");

		System.out.println("Pre-condition: an actived account is existing");
		Business.registerAccount(userAccount, mainTab);

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

		bookTicketPage.bookTicketAfterLocalDays(ticket);

		System.out.println("VP: Message \"Ticket booked successfully!\" displays");
		String actualSuccessMsg = bookTicketPage.getBookTicketSuccessMsgText();
		String expectedSuccessMsg = Message.BOOK_TICKET_SUCCESS_MSG.getMessage();
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Book ticket message is not displayed as expected");

		System.out.println(
				"VP: Ticket information display correctly (Depart Date,  Depart Station,  Arrive Station,  Seat Type,  Amount)");
		Map<TicketHeader, String> ticketInfo = bookTicketPage.getTicketInfo();

		Assert.assertEquals(ticketInfo.get(TicketHeader.DEPART_STATION), departFrom,
				"Depart Station is not displayed as expected");
		Assert.assertEquals(ticketInfo.get(TicketHeader.ARRIVE_STATION), arriveAt,
				"Arrive Station is not displayed as expected");
		Assert.assertEquals(ticketInfo.get(TicketHeader.SEAT_TYPE), seatType, "Seat Type is not displayed as expected");
		Assert.assertEquals(ticketInfo.get(TicketHeader.AMOUNT), String.valueOf(amountTicket),
				"Amount Ticket is not displayed as expected");
	}

}

package Railway;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Common.Utilities;
import Enums.TicketHeader;

public class BookTicketPage extends GeneralPage {
	// Locators
	private final By ddlDepartDate = By.xpath("//select[@name='Date']");
	private final By ddlDepartFrom = By.xpath("//select[@name='DepartStation']");
	private final By ddlArriveAt = By.xpath("//select[@name='ArriveStation']");
	private final By ddlSeatType = By.xpath("//select[@name='SeatType']");
	private final By ddlTicketAmount = By.xpath("//select[@name='TicketAmount']");

	private final By btnBookTicket = By.xpath("//input[@value='Book ticket']");

	private final By lblBookTicketSuccessMsg = By.xpath("//h1[normalize-space()='Ticket booked successfully!']");

	// Elements

	// Methods

	public void selectDepartDate(String date) {
		Utilities.selectByVisibleText(ddlDepartDate, date);
	}

	public void selectDepartFrom(String departFrom) {
		Utilities.selectByVisibleText(ddlDepartFrom, departFrom);
	}

	public void selectArriveAt(String arriveAt) {
		Utilities.selectByVisibleText(ddlArriveAt, arriveAt);
	}

	public void selectSeatType(String seatType) {
		Utilities.selectByVisibleText(ddlSeatType, seatType);
	}

	public void selectTicketAmount(String amount) {
		Utilities.selectByVisibleText(ddlTicketAmount, amount);
	}

	public void clickBookTicket() {
		Utilities.click(btnBookTicket);
	}

	public void selectDepartDateAfterDays(int days) {
		String targetDate = Utilities.getDateAfterDaysFromSelected(ddlDepartDate, days);

		Utilities.selectByVisibleText(ddlDepartDate, targetDate);
	}

	public void bookTicket(int date, String departFrom, String arriveAt, String seatType, String amount) {
		WebElement getArriveAt = Utilities.getElement(ddlArriveAt);
		selectDepartDateAfterDays(date);
		selectDepartFrom(departFrom);
		Utilities.waitUntilStale(getArriveAt);
		selectArriveAt(arriveAt);
		selectSeatType(seatType);
		selectTicketAmount(amount);
		clickBookTicket();
	}

	public String getBookTicketSuccessMsgText() {
		return Utilities.getElement(lblBookTicketSuccessMsg).getText();
	}

	public Map<String, String> getTicketInfo(int rowIndex) {
		Map<String, String> ticketInfo = new HashMap<>();

		ticketInfo.put(TicketHeader.DEPART_STATION.getHeaderText(),
				Utilities.getCellValueByHeader(TicketHeader.DEPART_STATION.getHeaderText(), rowIndex));
		ticketInfo.put(TicketHeader.ARRIVE_STATION.getHeaderText(),
				Utilities.getCellValueByHeader(TicketHeader.ARRIVE_STATION.getHeaderText(), rowIndex));
		ticketInfo.put(TicketHeader.SEAT_TYPE.getHeaderText(),
				Utilities.getCellValueByHeader(TicketHeader.SEAT_TYPE.getHeaderText(), rowIndex));
		ticketInfo.put(TicketHeader.AMOUNT.getHeaderText(),
				Utilities.getCellValueByHeader(TicketHeader.AMOUNT.getHeaderText(), rowIndex));

		return ticketInfo;
	}
}

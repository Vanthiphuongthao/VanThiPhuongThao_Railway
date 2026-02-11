package Railway;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

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

	private final String rowTableBookTicket = "//table//tr[td]/td[count(//th[normalize-space()='%s']/preceding-sibling::th)+1]";

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

	public void selectTicketAmount(Integer amount) {
		Utilities.selectByVisibleText(ddlTicketAmount, String.valueOf(amount));
	}

	public void clickBookTicket() {
		Utilities.click(btnBookTicket);
	}

	public String getDateAfterDaysFromDefault(String days) {
		return Utilities.getDateAfterDaysFromSelected(ddlDepartDate, days);
	}
//	public void selectDepartDateAfterDays(String days) {
//		String targetDate = Utilities.getDateAfterDaysFromSelected(ddlDepartDate, days);
//		Utilities.selectByVisibleText(ddlDepartDate, targetDate);
//	}

	// with local Date
	public void getDateAfterDaysWithLocalDate(String days) {
		String targetDate = Utilities.getDateAfterDays(days);
		Utilities.selectByVisibleText(ddlDepartDate, targetDate);
	}

	public void bookTicket(TicketInfo ticketInfo) {
		WebElement arriveAtElement = Utilities.getElement(ddlArriveAt);

		selectDepartDate(ticketInfo.getDepartDate());
		selectDepartFrom(ticketInfo.getDepartStation());

		Utilities.waitUntilStale(arriveAtElement);

		selectArriveAt(ticketInfo.getArriveStation());
		selectSeatType(ticketInfo.getSeatType());
		selectTicketAmount(ticketInfo.getAmount());

		clickBookTicket();
	}

	public void bookTicketAfterDaysFromDefault(TicketInfo ticketInfo) {
		WebElement arriveAtElement = Utilities.getElement(ddlArriveAt);

		getDateAfterDaysFromDefault(ticketInfo.getDepartDate());
		selectDepartFrom(ticketInfo.getDepartStation());

		Utilities.waitUntilStale(arriveAtElement);

		selectArriveAt(ticketInfo.getArriveStation());
		selectSeatType(ticketInfo.getSeatType());
		selectTicketAmount(ticketInfo.getAmount());

		clickBookTicket();
	}

	public void bookTicketAfterLocalDays(TicketInfo ticketInfo) {
		WebElement arriveAtElement = Utilities.getElement(ddlArriveAt);
		getDateAfterDaysWithLocalDate(ticketInfo.getDepartDate());
		selectDepartFrom(ticketInfo.getDepartStation());
		Utilities.waitUntilStale(arriveAtElement);
		selectArriveAt(ticketInfo.getArriveStation());
		selectSeatType(ticketInfo.getSeatType());
		selectTicketAmount(ticketInfo.getAmount());
		clickBookTicket();
	}

	public String getBookTicketSuccessMsgText() {
		return Utilities.getElement(lblBookTicketSuccessMsg).getText();
	}

	public String getValueByHeader(String headerName) {
		String xpath = String.format(rowTableBookTicket, headerName);
		return Utilities.getElement(By.xpath(xpath)).getText().trim();
	}

	public Map<TicketHeader, String> getTicketInfo() {

		Map<TicketHeader, String> ticketInfo = new HashMap<>();

		for (TicketHeader header : TicketHeader.values()) {
			ticketInfo.put(header, getValueByHeader(header.getHeaderText()));
		}
		return ticketInfo;
	}

}

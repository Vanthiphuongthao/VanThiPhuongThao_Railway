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

	// with local Date
	public void selectDepartDate(int days) {
		String targetDate = Utilities.getDateAfterDays(days);
		Utilities.selectByVisibleText(ddlDepartDate, targetDate);
	}

	public void bookTicket(TicketInfo ticketInfo) {
		WebElement arriveAtElement = Utilities.getElement(ddlArriveAt);
		selectDepartDateAfterDays(ticketInfo.getTargetDate());
		selectDepartFrom(ticketInfo.getDepartStation());
		Utilities.waitUntilStale(arriveAtElement);
		selectArriveAt(ticketInfo.getArriveStation());
		selectSeatType(ticketInfo.getSeatType());
		selectTicketAmount(ticketInfo.getAmount());
		clickBookTicket();
	}

	public void bookTicketAfterLocalDays(int date, String departFrom, String arriveAt, String seatType, String amount) {
		WebElement getArriveAt = Utilities.getElement(ddlArriveAt);
		selectDepartDate(date);
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

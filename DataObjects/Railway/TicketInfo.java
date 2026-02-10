package Railway;

public class TicketInfo {
	private int targetDate;
	private String departStation;
	private String arriveStation;
	private String seatType;
	private String amount;

	// constructor
	public TicketInfo(Integer targetDate, String departStation, String arriveStation, String seatType, String amount) {
		this.targetDate = targetDate;
		this.departStation = departStation;
		this.arriveStation = arriveStation;
		this.seatType = seatType;
		this.amount = amount;
	}

	// getters
	public Integer getTargetdate() {
		return targetDate;
	}
	
	public String getDepartStation() {
		return departStation;
	}

	public String getArriveStation() {
		return arriveStation;
	}

	public String getSeatType() {
		return seatType;
	}

	public String getAmount() {
		return amount;
	}
}

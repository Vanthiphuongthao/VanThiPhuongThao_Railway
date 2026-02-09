package Railway;

public class TicketInfo {
	private String departStation;
	private String arriveStation;
	private String seatType;
	private String amount;

	// constructor
	public TicketInfo(String departStation, String arriveStation, String seatType, String amount) {
		this.departStation = departStation;
		this.arriveStation = arriveStation;
		this.seatType = seatType;
		this.amount = amount;
	}

	// getters
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

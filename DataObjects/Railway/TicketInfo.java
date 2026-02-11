package Railway;

public class TicketInfo {
	private int departDate;
	private String departStation;
	private String arriveStation;
	private String seatType;
	private String amount;

	// constructor
	public TicketInfo(Integer departDate, String departStation, String arriveStation, String seatType, String amount) {
		this.departDate = departDate;
		this.departStation = departStation;
		this.arriveStation = arriveStation;
		this.seatType = seatType;
		this.amount = amount;
	}

	public int getTargetDate() {
		return departDate;
	}

	public void setTargetDate(int targetDate) {
		this.departDate = targetDate;
	}

	public String getDepartStation() {
		return departStation;
	}

	public void setDepartStation(String departStation) {
		this.departStation = departStation;
	}

	public String getArriveStation() {
		return arriveStation;
	}

	public void setArriveStation(String arriveStation) {
		this.arriveStation = arriveStation;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}

package Enums;

public enum SeatPrice {
	HS("HS", "310000"),
	SS("SS", "335000"),
	SSC("SSC", "360000"),
	HB("HB", "410000"),
	SB("SB", "460000"),
	SBC("SBC", "510000");

	private final String code;
	private final String expectedPrice;

	public String getCode() {
		return code;
	}

	public String getExpectedPrice() {
		return expectedPrice;
	}

	SeatPrice(String code, String expectedPrice) {
        this.code = code;
        this.expectedPrice = expectedPrice;
    }
	
}

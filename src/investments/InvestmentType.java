package investments;

public enum InvestmentType {
		BANK("bank"),
		HOLDING("holding"),
		TRADING("trading"),
		BITCOIN("bitcoin"),
		ESTATE("estate");
	
	private final String name;
	
	private InvestmentType(String name) {
		this.name=name;
	}
		
	public String getName() {
		return this.name;
	}
	
	public static InvestmentType getType(String name) {
		return InvestmentType.valueOf(name);
	}
}

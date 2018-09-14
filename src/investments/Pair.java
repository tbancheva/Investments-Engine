package investments;

public class Pair {
	private int investment;
	private int year;
	
	public Pair() {}
	
	public Pair(int i, int y) {
		this.investment = i;
		this.year = y;
	}
	
	public int getInvestment() {
		return investment;
	}
	public void setInvestment(int investment) {
		this.investment = investment;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
}

package investments;

import java.util.Random;

public class InvestmentOption {
	private InvestmentType type;
	private String name;

	private double riskPerCent;
	private double[] revenuePerCent;
	private int incomingTransactionTax;
	private int outcomingTransactionTax;

	public InvestmentOption(InvestmentType type, String name, double riskPerCent, double[] revenuePerCent, int inTax,
			int outTax) {
		this.type = type;
		this.name = name;
		this.riskPerCent = riskPerCent;
		this.revenuePerCent = revenuePerCent;
		this.incomingTransactionTax = inTax;
		this.outcomingTransactionTax = outTax;
	}

	public double calculatePossibleProfit(int sum, int period) {
		double result = sum - incomingTransactionTax;

		for (int i = 0; i < period; i++) {
			double profit;

			if (i < revenuePerCent.length) {
				profit = result * revenuePerCent[i] / 100;
			} else {
				profit = result * revenuePerCent[revenuePerCent.length - 1] / 100;
			}

			result += profit;
		}

		return result - sum;
	}

	public double oneYearProfit(double money, int currentYear) {
		if(currentYear >= revenuePerCent.length) {
			currentYear = revenuePerCent.length -1;
		}
		
		return money*revenuePerCent[currentYear-1]/100;
	}
	
	// simulating the possibility of loosing money according to risk%
	public boolean isLoosingMoney() {
		Random number = new Random();
		double rangeMin = 0;
		double rangeMax = 100;
		double chance = rangeMin + (rangeMax - rangeMin) * number.nextDouble();
		return chance < riskPerCent;
	}

	public InvestmentType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public double getRiskPerCent() {
		return riskPerCent;
	}

	public double[] getRevenuePerCent() {
		return revenuePerCent;
	}

	public int getIncomingTransactionTax() {
		return incomingTransactionTax;
	}

	public int getOutcomingTransactionTax() {
		return outcomingTransactionTax;
	}
	
	public int getRevenuePerCentSize() {
		return revenuePerCent.length;
	}


}

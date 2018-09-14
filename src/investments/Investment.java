package investments;

import investments.engine.Client;

public class Investment extends Thread {
	private static final int ONE_YEAR_SIMULATED_TIME = 5000;
	
	private InvestmentOption option;
	private int period;
	private int startingSum;
	private double currentSum;

	private int currentYear;

	private boolean isOver;
	private boolean isStopped;

	public Investment(InvestmentOption opt, int sum, int period) {
		this.option = opt;
		this.period = period;
		this.startingSum = sum;
		this.currentSum = sum;
		this.currentYear = 1;
		this.isOver = false;
		this.isStopped = false;
	}

	// simulation of passing one year of the investment
	public String yearPassing() {
		if (option.isLoosingMoney()) {
			currentSum = 0;
			isOver = true;
			return "Unfortunately there has been a misshap with the investment in " + option.getName()
					+ " and the money invested there was lost";
		}

		double currentProfit = option.oneYearProfit(currentSum, currentYear);

		// the client gets taxed for having added the money
		if (currentYear == 1) {
			currentProfit -= option.getIncomingTransactionTax();
		}

		currentSum += currentProfit;
		
		//rounding up the numbers to 2 places after the decimal for accurate money output
		double overAllProfit = Math.round((currentSum - startingSum) * 100.0) / 100.0;
		
		String result = "Current profit of the investment in " + option.getName() + ": " + overAllProfit
				+ " for " + currentYear + " years passed";

		currentYear++;

		return result;
	}

		
	public void run() {
		while (!isOver && currentYear <= period) {
			try {
				Thread.sleep(ONE_YEAR_SIMULATED_TIME);
				if (isStopped) {
					synchronized (this) {
						this.wait();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
					
			System.out.println(yearPassing());
		}

		isOver = true;

		Client.addMoneyAfterFinishingInvestment(currentSum);
						
		System.out.println("The investment in " + option.getName() + " is over. Overall profit: " + getOverallProfit());
	}
	
	
	public double getOverallProfit() {
		//rounding up the numbers to 2 places after the decimal for accurate money output
		double overAllProfit = Math.round((currentSum - startingSum) * 100.0) / 100.0;
		
		return overAllProfit;
	}
	
		
	public String getInvestmentOptionName() {
		return this.option.getName();
	}

	public void extractMoney(int sum) {
		currentSum -= sum + option.getOutcomingTransactionTax();
		startingSum-=sum;
	}

	public double getCurrentSum() {
		return this.currentSum;
	}

	public void finishInvestment() {
		isOver = true;
	}

	public void pause() {
		isStopped = true;
	}
	
	public void restart() {
		isStopped = false;
	}

	public boolean isFinished() {
		return isOver;
	}


}

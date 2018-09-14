package investments.engine;

import java.util.ArrayList;
import java.util.List;

import investments.Investment;
import investments.NoInvestmentWithSuchNameException;

public class Client {

	private List<Investment> currentInvestments;
	
	private static double moneyAtTheEnd;
	private static double moneyAtStart;
	
	public Client() {
		currentInvestments = new ArrayList<>();
		moneyAtTheEnd = 0;
	}

	public void addInvestment(Investment inv) {
		currentInvestments.add(inv);
	}

	public void pauseInvestments() {
		for (Investment inv : currentInvestments) {
			inv.pause();
		}
	}
	
	public void startInvestments() {
		
		for (Investment inv : currentInvestments) {
			if(inv.getState().equals(Thread.State.NEW)) {
				inv.start();  //starts threads that were newly added
			}
			 
		}
		
		for (Investment inv : currentInvestments) {
			synchronized (inv) {
				inv.notify();  //restarts threads that were stopped and are waiting
			}
			inv.restart();   
		}
		
	}

	public boolean hasSomeTimePassed() {
		return currentInvestments.get(0).isAlive();
	}

	public Investment getInvestment(String name) throws NoInvestmentWithSuchNameException {
		for (Investment investment : currentInvestments) {
			if (investment.getInvestmentOptionName().equals(name)) {
				return investment;
			}
		}
		throw new NoInvestmentWithSuchNameException();
	}

	public static synchronized void addMoneyAfterFinishingInvestment(double sum) {
		moneyAtTheEnd += sum;
	}
	
	public static synchronized void addInitialMoney(double sum) {
		moneyAtStart += sum;
	}
		
	
	public String afterFinishingInvestment() {
		return  "Overall profit: " + getEndResult();
	}
	
	public double getEndResult() {
		//rounding up the numbers to 2 places after the decimal for accurate money output
		double overallProfit = Math.round((moneyAtTheEnd-moneyAtStart) * 100.0) / 100.0; 
		return overallProfit;
	}
	
	//for easier unit testing
	public void joinInvestments() {
		for (Investment inv : currentInvestments) {
			try {
				inv.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

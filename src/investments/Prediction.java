package investments;

import java.util.ArrayList;
import java.util.List;

public class Prediction {
	private double sum;
	private List<Pair> pairs;

	public Prediction() {
		pairs = new ArrayList<>();
		this.sum = 0;
	}

	public void addPair(Pair p) {
		boolean flag = false;
		for(Pair p2 :pairs) {
			if(p.getInvestment() == p2.getInvestment()) {
				flag = true;
				p2.setYear(p2.getYear()+1);
			}
		}
		
		if(!flag) {
			pairs.add(p);
		}
	}

	public void print() {
		for (Pair p : pairs) {
			System.out.println(p.getInvestment() + " " + " for " + p.getYear() + " years " );
		}
		System.out.println(sum);
	}
	
	public List<Pair> getPairs(){
		return this.pairs;
	}

	public double getSum() {
		return sum;
	}

	public void addSum(double sum) {
		this.sum += sum;
	}

}

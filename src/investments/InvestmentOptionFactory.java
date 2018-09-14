package investments;

import java.util.ArrayList;
import java.util.List;

public class InvestmentOptionFactory {
	private String characteristics;
	
	public InvestmentOptionFactory(String characteristics) {
		this.characteristics = characteristics;
	}
	
	public InvestmentOption createInvestment() {
		double risk = getRisk();
		InvestmentType type = getType();
		String name = getName();
		int inTax = getInTax();
		int outTax = getOutTax();
		double[] revenue = getRevenue();
		
		return new InvestmentOption(type, name, risk, revenue, inTax, outTax);
	}
	
	public double[] getRevenue() {
		
		List<Double> revenues = new ArrayList<>();

		String[] periods = characteristics.split("revenue: |inTax:")[1].split(",");
		
		for (int i = 0; i < periods.length -1 ; i++) {
			String numbYears = periods[i].split(":")[0].trim();
						
			if (!numbYears.equals("*")) {
				int numberYears = Integer.parseInt(numbYears);
				double perCent = Double.parseDouble(periods[i].split(":|%")[1].trim());

				while (numberYears > 0) {
					revenues.add(perCent);
					numberYears--;
				}
			}
			else {
				revenues.add(Double.parseDouble(periods[i].split(":|%")[1].trim()));
			}
		}

		double[] revenue = new double[revenues.size()];
		for (int i = 0; i < revenues.size(); i++) {
			revenue[i] = revenues.get(i);
		}

		return revenue;
	}

	public double getRisk() {
		return Double.parseDouble(characteristics.split("risk: |%")[1]);
	}

	public InvestmentType getType() {
		String type = characteristics.split("type: |,")[1].toUpperCase();
		return InvestmentType.getType(type);
	}

	public String getName() {
		return characteristics.split("name: |,")[2];
	}

	public int getInTax() {
		return Integer.parseInt(characteristics.split("inTax: |,")[7]);
	}

	public int getOutTax() {
		return Integer.parseInt(characteristics.split("outTax: |,")[8]);
	}
}

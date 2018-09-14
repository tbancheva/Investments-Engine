import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import investments.InvestmentOption;
import investments.InvestmentType;

public class InvestmentOptionTest {
	
	private static InvestmentOption option;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		InvestmentType type = InvestmentType.BANK;
		String name = "Unicredit Bulbank";
		double riskPerCent = 0.3;
		double[] revenue = { 4, 1, 1, 1, 0.1, 0.1 };
		int inTax = 3;
		int outTax = 5;
		
		option = new InvestmentOption(type, name, riskPerCent, revenue, inTax, outTax);
	}

	@Test
	public void calculateProfitTest() {
		int sum = 1000;
		int twoYears = 2;
		int fiveYears = 5;
		int tenYears = 10;
		
		double profitForTwoYears = option.calculatePossibleProfit(sum, twoYears);
		double profitForFiveYears = option.calculatePossibleProfit(sum, fiveYears);
		double profitForTenYears = option.calculatePossibleProfit(sum, tenYears);
				
		assertEquals(47.4, profitForTwoYears, 2);
		assertEquals(69.58, profitForFiveYears, 2);
		assertEquals(74.89, profitForTenYears, 2);
		
	}
	
	@Test
	public void oneYearProfitTest() {
			
		double profitForOneYear = option.oneYearProfit(1000, 1);
		double profitForTwoYears = option.oneYearProfit(1040, 2);
						
		assertEquals(40, profitForOneYear, 2);
		assertEquals(10.4, profitForTwoYears, 2);
	
		
	}

}

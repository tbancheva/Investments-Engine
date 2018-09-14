import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import investments.Investment;
import investments.InvestmentOption;
import investments.InvestmentOptionFactory;
import investments.NoInvestmentWithSuchNameException;
import investments.engine.Client;

public class ClientTest {
	private static Client client;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		client = new Client();

		String command = "type: bank, name: Unicredit Bulbank, risk: 0.3%,"
				+ " revenue: 1:4%, 3:1%, *:0.1%, inTax: 3, outTax: 5";
		String command2 = "type: bank, name: Raiffeisen Bank, risk: 0.5%,"
				+ " revenue: 1:5%, 1:2%, *:0.3%, inTax: 4, outTax: 15";

		InvestmentOption invOpt1 = new InvestmentOptionFactory(command).createInvestment();
		InvestmentOption invOpt2 = new InvestmentOptionFactory(command2).createInvestment();
		
		Investment inv1 = new Investment(invOpt1, 1000, 2);
		Investment inv2 = new Investment(invOpt2, 1000, 2);
		
		client.addInvestment(inv1);
		client.addInvestment(inv2);
		
		client.startInvestments();
		client.joinInvestments();
	}

	@Test
	public void getEndResultTest() {
		assertEquals(2114.29, client.getEndResult(), 2);
	}
	
	@Test
	public void runningInvestmentTest() throws NoInvestmentWithSuchNameException {
		assertEquals(47.37, client.getInvestment("Unicredit Bulbank").getOverallProfit(), 2);
	}
}

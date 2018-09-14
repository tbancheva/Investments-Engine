import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import investments.InvestmentOptionFactory;

public class InvestmentFactoryTest {

	private static InvestmentOptionFactory factory;

	@BeforeClass
	public static void setUpBeforeClass() {
		String command = "type: bank, name: Unicredit Bulbank, risk: 0.3%,"
				+ " revenue: 1:4%, 3:1%, *:0.1%, inTax: 3, outTax: 5";
		
		factory = new InvestmentOptionFactory(command);
	}

	@Test
	public void getRiskTest() {
		assertEquals(0.3, factory.getRisk(), 2);
	}

	@Test
	public void getInvestmentTypeTest() {
		assertEquals("BANK", factory.getType().toString());
	}

	@Test
	public void getNameTest() {
		assertEquals("Unicredit Bulbank", factory.getName());
	}

	@Test
	public void getInTaxTest() {
		assertEquals(3, factory.getInTax());
	}

	@Test
	public void getOutTaxTest() {
		assertEquals(5, factory.getOutTax());
	}

	@Test
	public void getRevenueTest() {
		double[] expected = { 4, 1, 1, 1, 0.1 };
		double[] actual = factory.getRevenue();
		
		for(int i = 0; i<expected.length ;i ++) {
			assertEquals(expected[i], actual[i],2);
		}
	}

}

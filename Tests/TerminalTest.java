import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import investments.InvestmentOptions;
import investments.engine.Client;
import investments.engine.Terminal;

public class TerminalTest {
	
	private static Terminal terminal;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InvestmentOptions availableInvestments = new InvestmentOptions();
		Client client = new Client();
		terminal = new Terminal(availableInvestments, client);
	}

	@Test
	public void loadInvestmentsTest() {
		assertEquals(1, terminal.getAvailableInvestments().getSize());
		assertEquals("Unicredit Bulbank", terminal.getAvailableInvestments().getIndex(0).getName());
	}

}

package investments.commands;

import investments.ImpossibleTransactionException;
import investments.Investment;
import investments.InvestmentEngineException;
import investments.engine.Client;

public class ExtractSum implements Command {

	private Client client;

	public ExtractSum(Client client) {
		this.client = client;
	}

	public String execute(String command) throws InvestmentEngineException {
		// extract 800 from Unicredit Bulbank

		if (command.split(" ").length < 4) {
			throw new MissingCommandArgumentsException();
		}

		String invName = command.split("from")[1].trim();
		int extractingSum = Integer.parseInt(command.split(" ")[1]);

		Investment currentInv = client.getInvestment(invName);

		if (currentInv.getCurrentSum() > extractingSum) {
			currentInv.extractMoney(extractingSum);
			return "" + extractingSum + " extracted from: " + invName;
		} else {
			throw new ImpossibleTransactionException();
		}

	}
}

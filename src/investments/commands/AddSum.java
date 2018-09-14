package investments.commands;

import investments.Investment;
import investments.InvestmentEngineException;
import investments.InvestmentOptions;
import investments.engine.Client;

public class AddSum implements Command{
	
	private InvestmentOptions allInvestments;
	private Client client;

	public AddSum(InvestmentOptions allInv, Client client) {
		this.allInvestments = allInv;
		this.client = client;
	}
	
	public String execute(String command) throws InvestmentEngineException {
		//add 1000 3 Unicredit Bulbank 
		
		String[] arguements = command.split(" ");
		
		if(arguements.length < 4) {
			throw new MissingCommandArgumentsException();
		}
		
		int sum = Integer.parseInt(arguements[1]);
		int period =  Integer.parseInt(arguements[2]);
		
		StringBuilder invName = new StringBuilder();
		for(int i=3;i<arguements.length;i++) {
			invName.append(arguements[i] + " ");
		}
		
		String name = invName.toString().trim();
				
		Investment investment = new Investment(allInvestments.getByName(name), sum, period);
		client.addInvestment(investment);
		
		if(!client.hasSomeTimePassed()) {
			Client.addInitialMoney(sum);
		}
		
		return "Investment added";
	}
}

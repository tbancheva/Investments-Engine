package investments.commands;

import investments.engine.Client;

public class Start implements Command{

	private Client client;
	
	public Start(Client client) {
		this.client = client;
	}
	
	@Override
	public String execute(String command) {
				
		client.startInvestments();    
				
		return "Time started";
	}

}

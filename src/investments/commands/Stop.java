package investments.commands;

import investments.engine.Client;

public class Stop implements Command{
private Client client;
	
	public Stop(Client client) {
		this.client = client;
	}
	
	@Override
	public String execute(String command) {
		
		client.pauseInvestments();
		
		return "Time stoped";
	}


}

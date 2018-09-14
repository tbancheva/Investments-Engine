package investments.commands;

import investments.InvestmentEngineException;

public interface Command {
	public String execute(String command) throws InvestmentEngineException;
}

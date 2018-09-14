package investments.commands;

import investments.InvestmentEngineException;

public abstract class CommandException extends InvestmentEngineException{
	
	private static final long serialVersionUID = 1L;

	public CommandException() {
		super();
	}
	
	public CommandException(String message) {
		super(message);
	}
	
	public CommandException(String message, Throwable t) {
		super(message, t);
	}
	
	public CommandException(Throwable t) {
		super(t);
	}
	
}
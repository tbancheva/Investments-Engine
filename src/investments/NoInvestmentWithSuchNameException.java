package investments;

/*
* Gets thrown when the client wants to add investment but he has typed a wrong name
* or there just isn't an investment with the exact input name
*/

public class NoInvestmentWithSuchNameException extends InvestmentEngineException{
	private static final long serialVersionUID = 1L;

	public NoInvestmentWithSuchNameException() {
		super("There is no investment with that name");
	}
	
	public NoInvestmentWithSuchNameException(String message) {
		super(message);
	}
	
	public NoInvestmentWithSuchNameException(String message, Throwable t) {
		super(message, t);
	}
	
	public NoInvestmentWithSuchNameException(Throwable t) {
		super(t);
	}
	
}

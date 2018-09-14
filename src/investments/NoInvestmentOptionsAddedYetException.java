package investments;

/*
* Gets thrown when the client wants to add investment but he has typed a wrong name
*/

public class NoInvestmentOptionsAddedYetException extends InvestmentEngineException{
	private static final long serialVersionUID = 1L;

	public NoInvestmentOptionsAddedYetException() {
		super("There is no investment with that name");
	}
	
	public NoInvestmentOptionsAddedYetException(String message) {
		super(message);
	}
	
	public NoInvestmentOptionsAddedYetException(String message, Throwable t) {
		super(message, t);
	}
	
	public NoInvestmentOptionsAddedYetException(Throwable t) {
		super(t);
	}
	
}
package investments;

/*
 * Gets thrown when the client wants to extract more money than he has
 */

public class ImpossibleTransactionException extends InvestmentEngineException{
	private static final long serialVersionUID = 1L;

	public ImpossibleTransactionException() {
		super("There is not enough money to complete transaction");
	}
	
	public ImpossibleTransactionException(String message) {
		super(message);
	}
	
	public ImpossibleTransactionException(String message, Throwable t) {
		super(message, t);
	}
	
	public ImpossibleTransactionException(Throwable t) {
		super(t);
	}
	
}
package investments;

public class InvestmentEngineException extends Exception{
	private static final long serialVersionUID = 1L;

	public InvestmentEngineException() {
		super();
	}
	
	public InvestmentEngineException(String message) {
		super(message);
	}
	
	public InvestmentEngineException(String message, Throwable t) {
		super(message, t);
	}
	
	public InvestmentEngineException(Throwable t) {
		super(t);
	}
	
}
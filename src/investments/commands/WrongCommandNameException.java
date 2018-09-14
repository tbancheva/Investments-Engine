package investments.commands;

/*
 * Gets thrown when the client has typed a command that does not exist
 */

public class WrongCommandNameException extends CommandException{

	private static final long serialVersionUID = 1L;

	public WrongCommandNameException() {
		super("There is no such command");
	}
	
	public WrongCommandNameException(String message) {
		super(message);
	}
	
	public WrongCommandNameException(String message, Throwable t) {
		super(message, t);
	}
	
	public WrongCommandNameException(Throwable t) {
		super(t);
	}
}


package logic.Exceptions;

public class CommandParseException extends GameException {

	private static final long serialVersionUID = 1L;

	public CommandParseException() {
		super();
	}
	
	public CommandParseException(String msg) {
		super(msg);
	}

	public CommandParseException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
	public CommandParseException(Throwable cause) {
		super(cause);
	}

	

}

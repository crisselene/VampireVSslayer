package logic.Exceptions;

public class NumberFormatException extends CommandParseException {
	
	private static final long serialVersionUID = 5561894634760356539L;

	public NumberFormatException(String msg) {
		super(msg);
	}
	public NumberFormatException(String msg,Throwable cause) {
		super(msg,cause);
	}
	public NumberFormatException() {
		super();
	}
	
	public NumberFormatException(Throwable cause) {
		super(cause);
	}
}

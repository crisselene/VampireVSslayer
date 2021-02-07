package logic.Exceptions;

public class CommandExecuteException extends GameException {


	private static final long serialVersionUID = 1L;

	public CommandExecuteException(String msg) {
		super(msg);
	}
	
	public CommandExecuteException(String msg,Throwable cause) {
		super(msg,cause);
	}
	public CommandExecuteException() {
		super();
	}
	
	public CommandExecuteException(Throwable cause) {
		super(cause);
	}
}

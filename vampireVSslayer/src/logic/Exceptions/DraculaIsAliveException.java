package logic.Exceptions;

public class DraculaIsAliveException extends CommandExecuteException {

	private static final long serialVersionUID = -6522978472373843243L;

	public DraculaIsAliveException(String msg) {
		super(msg);
	}
	public DraculaIsAliveException() {
		super();
	}

	public DraculaIsAliveException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
	public DraculaIsAliveException(Throwable cause) {
		super(cause);
	}
}

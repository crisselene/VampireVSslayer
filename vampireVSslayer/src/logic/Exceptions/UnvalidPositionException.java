package logic.Exceptions;

public class UnvalidPositionException extends CommandExecuteException{

	private static final long serialVersionUID = 9163377744206947931L;

	public UnvalidPositionException(String msg) {
		super(msg);
	}
	
	public UnvalidPositionException(String msg,Throwable cause) {
		super(msg,cause);
	}
	public UnvalidPositionException() {
		super();
	}
	
	public UnvalidPositionException(Throwable cause) {
		super(cause);
	}

}

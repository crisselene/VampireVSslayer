package logic.Exceptions;

public class NotEnoughCoinsException extends CommandExecuteException {

	private static final long serialVersionUID = 1427531001212864369L;

	public NotEnoughCoinsException(String msg) {
		super(msg);
	}
	public NotEnoughCoinsException(String msg,Throwable cause) {
		super(msg,cause);
	}
	public NotEnoughCoinsException() {
		super();
	}
	
	public NotEnoughCoinsException(Throwable cause) {
		super(cause);
	}


}

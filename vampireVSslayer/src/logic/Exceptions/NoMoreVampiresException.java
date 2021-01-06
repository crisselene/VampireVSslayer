package logic.Exceptions;

public class NoMoreVampiresException extends CommandExecuteException {

	private static final long serialVersionUID = 338770441097856146L;

	public NoMoreVampiresException(String msg) {
		super(msg);
	}

}

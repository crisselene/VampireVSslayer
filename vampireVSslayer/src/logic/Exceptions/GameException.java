package logic.Exceptions;

public class GameException extends Exception{

	private static final long serialVersionUID = 2009008696959476590L;

	public GameException() {
		super();
	}
	
	public GameException(String msg) {
		super(msg);
	}

	public GameException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
	public GameException(Throwable cause) {
		super(cause);
	}

}

package control.Commands;

import logic.Game;
import logic.Exceptions.CommandParseException;

public class ExitCommand extends Command {

	private static final String NAME = "exit";
	private static final String SHORTCUT = "e";
	private static final String DETAILS = "[e]xit";
	private static final String HELP = "exit game";
	
	public ExitCommand() {
		//Deben ser static strings
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public boolean execute(Game game) {
		game.doExit();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}

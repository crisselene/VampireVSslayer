package control.Commands;

import logic.Game;
import logic.Exceptions.CommandExecuteException;
import logic.Exceptions.CommandParseException;

public class SaveCommand extends Command {

	private static final String NAME = "save";
	private static final String SHORTCUT = "s";
	private static final String DETAILS = "[s]ave <fileName>";
	private static final String HELP = "save the game";
	private static final int ARGS = 2;
	private String fileName;

	public SaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.save(fileName);
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws NumberFormatException, CommandParseException {
		if(commandWords.length > 1)
		{
			fileName= commandWords[1] + ".dat";
			return parseParamsCommand(commandWords, ARGS);
		}
		else return null;
		
	}

}

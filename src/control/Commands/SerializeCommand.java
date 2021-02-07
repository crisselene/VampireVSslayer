package control.Commands;

import logic.Game;
import logic.Exceptions.CommandExecuteException;
import logic.Exceptions.CommandParseException;

public class SerializeCommand extends Command {
	
	private static final String NAME = "serialize";
	private static final String SHORTCUT = "z";
	private static final String DETAILS = "Seriali[z]e";
	private static final String HELP = "Serializes the board.";

	public SerializeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
		
	}

	@Override
	public boolean execute(Game game){
		System.out.println(game.serialize());
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws NumberFormatException, CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}

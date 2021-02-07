package control.Commands;

import logic.Game;
import logic.Exceptions.CommandParseException;

public class HelpCommand extends Command {

	private static final String NAME = "help";
	private static final String SHORTCUT = "h";
	private static final String DETAILS = "[h]elp";
	private static final String HELP = "show this help";
	
	
	
	public HelpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public boolean execute(Game game) {
		CommandGenerator.recorrerArrayHelp();		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}

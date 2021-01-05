package control.Commands;

import logic.Game;
import logic.Exceptions.CommandParseException;

public class SuperCoinsCommand extends Command {

	private static final String NAME = "coins";
	private static final String SHORTCUT = "c";
	private static final String DETAILS = "[c]oins";
	private static final String HELP = "add 1000 coins";
	
	public SuperCoinsCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.superMonedas();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}

package control.Commands;

import logic.Game;
import logic.Exceptions.CommandParseException;

public class GarlicPushCommand extends Command {
	
	private static final String NAME = "garlic";
	private static final String SHORTCUT = "g";
	private static final String DETAILS = "[g]arlic ";
	private static final String HELP = "pushes back vampires";
	
	public GarlicPushCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		if(game.pushVampires()) {
			game.update();
			return true;
		}		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {		
		return parseNoParamsCommand(commandWords);
	}

}

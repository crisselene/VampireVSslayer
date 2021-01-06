package control.Commands;

import logic.Game;
import logic.Exceptions.CommandExecuteException;
import logic.Exceptions.CommandParseException;
import logic.Exceptions.NotEnoughCoinsException;

public class GarlicPushCommand extends Command {
	
	private static final String NAME = "garlic";
	private static final String SHORTCUT = "g";
	private static final String DETAILS = "[g]arlic ";
	private static final String HELP = "pushes back vampires";
	
	public GarlicPushCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			if(game.pushVampires()) {
				game.update();
				return true;
			}
		} catch (NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException("fallo al pushear los vampiros");
		}		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {		
		return parseNoParamsCommand(commandWords);
	}

}

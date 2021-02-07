package control.Commands;

import logic.Game;
import logic.Exceptions.CommandExecuteException;
import logic.Exceptions.CommandParseException;
import logic.Exceptions.NotEnoughCoinsException;

public class LightFlashCommand extends Command {
	
	private static final String NAME = "light";
	private static final String SHORTCUT = "l";
	private static final String DETAILS = "[l]ight";
	private static final String HELP = "kills all the vampires";
	
	public LightFlashCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			if(game.lightVampires()) {
				game.update();
				return true;
			}
		} catch (NotEnoughCoinsException e) {
			System.out.println("[ERROR]: "+ e.getMessage());
			throw new CommandExecuteException("[ERROR]: Failed to light flash");
		}		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		return parseNoParamsCommand(commandWords);
	}

}

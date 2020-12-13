package control.Commands;

import logic.Game;

public class LightFlashCommand extends Command {
	
	private static final String NAME = "light";
	private static final String SHORTCUT = "l";
	private static final String DETAILS = "[l]ight";
	private static final String HELP = "kills all the vampires";
	
	public LightFlashCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		if(game.lightVampires()) {
			game.update();
			return true;
		}		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}

}

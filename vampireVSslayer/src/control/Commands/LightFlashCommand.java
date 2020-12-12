package control.Commands;

import logic.Game;

public class LightFlashCommand extends Command {
	
	public LightFlashCommand() {
		super("light", "l", "[l]ight", "light the vampires");
		// TODO Auto-generated constructor stub
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

package control.Commands;

import logic.Game;

public class GarlicPushCommand extends Command {
	
	public GarlicPushCommand() {
		super("garlic", "g", "[g]arlic", "push the vampires 1 position");
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
	public Command parse(String[] commandWords) {		
		return parseNoParamsCommand(commandWords);
	}

}

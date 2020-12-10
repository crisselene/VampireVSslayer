package control.Commands;

import logic.Game;

public class GarlicPushCommand extends Command {

	private static final int COSTE=10;
	
	public GarlicPushCommand() {
		super("garlic", "g", "[g]arlic", "push the vampires 1 position");
	}

	@Override
	public boolean execute(Game game) {
		if(game.pushVampires(COSTE)) {
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

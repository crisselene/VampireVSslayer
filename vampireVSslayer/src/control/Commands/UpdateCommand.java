package control.Commands;

import logic.Game;

public class UpdateCommand extends Command {

	public UpdateCommand() {
		super("none", "n", "[n]one", "updates the game");
	}

	@Override
	public boolean execute(Game game) {		
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		
		return parseNoParamsCommand(commandWords);
	}

}

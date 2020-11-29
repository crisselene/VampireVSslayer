package control.Commands;

import logic.Game;

public class ResetCommand extends Command{
	
	public ResetCommand() {
		//Deben ser static strings
		super("reset", "r", "[R]eset", "reset game");
	}
	
	public boolean execute(Game game) {
		game.doReset();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}

}

package control.Commands;

import logic.Game;

public class ExitCommand extends Command {

	public ExitCommand() {
		//Deben ser static strings
		super("exit", "e", "[E]xit", "exits the game");
	}
	
	public boolean execute(Game game) {
		game.doExit();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		return parseNoParamsCommand(commandWords);
	}

}

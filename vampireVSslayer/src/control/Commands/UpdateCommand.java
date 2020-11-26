package control.Commands;

import logic.Game;

public class UpdateCommand extends Command {

	public UpdateCommand() {
		super("none", "n", "[n]one", "updates the game");
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords[0] == "" || commandWords.length == 0)
		{
			return this;
		}
		return parseNoParamsCommand(commandWords);
	}

}

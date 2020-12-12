package control.Commands;

import logic.Game;

public class UpdateCommand extends Command {

	public UpdateCommand() {
		super("none", "n", "[n]one", "update game");
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(String.valueOf(commandWords[0]).equals(""))
		{
			//System.out.println("..." +commandWords[0]+ "...");
			return this;
		}
		return parseNoParamsCommand(commandWords);
	}

}

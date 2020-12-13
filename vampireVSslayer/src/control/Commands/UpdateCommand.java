package control.Commands;

import logic.Game;

public class UpdateCommand extends Command {
	
	private static final String NAME = "none";
	private static final String SHORTCUT = "n";
	private static final String DETAILS = "[n]one | []";
	private static final String HELP = "update";

	public UpdateCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
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
			return this;
		}
		return parseNoParamsCommand(commandWords);
	}

}

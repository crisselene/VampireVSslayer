package control.Commands;

import java.io.IOException;

import logic.Game;
import logic.Exceptions.CommandExecuteException;
import logic.Exceptions.CommandParseException;

public class SaveCommand extends Command {

	private static final String NAME = "save";
	private static final String SHORTCUT = "s";
	private static final String DETAILS = "[S]ave <filename>";
	private static final String HELP = "Save the state of the game to a file.";
	private static final int ARGS = 2;
	private String fileName;

	public SaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);		
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.save(fileName);
		} catch (IOException e) {
			throw new CommandExecuteException("fallo al guardar el fichero");
		}
		System.out.println("Game successfully saved to file "+fileName+".");
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		//if(commandWords.length > 1)
		if(commandWords.length > 1)
		{
			fileName= commandWords[1] + ".dat";			
		}
		return parseParamsCommand(commandWords, ARGS);
		
	}

}

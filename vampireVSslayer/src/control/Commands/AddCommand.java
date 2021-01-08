package control.Commands;

import logic.Game;
import logic.Exceptions.CommandExecuteException;
import logic.Exceptions.CommandParseException;

public class AddCommand extends Command {
	
	String msgUnvalidAdd = "Unvalid argument for add slayer command, number expected: [a]dd <x> <y>";
	private static final String NAME = "add";
	private static final String SHORTCUT = "a";
	private static final String DETAILS = "[a]dd <x> <y>";
	private static final String HELP = "add a slayer in position x, y";
	private static final int ARGS=3;
	private int x;
	private int y;	

	public AddCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		// Añadiriamos slayer, pero tenemos que crear las nuevas listas
		boolean creado;
		try {
			creado = game.addSlayer(x,y);
		}
		catch(CommandExecuteException ex){
			creado = false;
			System.out.println("[ERROR]: "+ ex.getMessage());//unvalid position
			throw new CommandExecuteException("[ERROR]: Failed to add slayer");
		}
		if(creado) {
			game.update();
			return true;
		}
		return false;
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
			
		if (commandWords.length == 3) {
				// Si no ha introducido caracteres
				try {
					if (isNumeric(commandWords[1]) && isNumeric(commandWords[2])) {
						x = Integer.parseInt(commandWords[1]);// Lo convertimos en numero
						y = Integer.parseInt(commandWords[2]);
						// Retornamos dependiendo de los argumentos que habia en el parse
						return parseParamsCommand(commandWords, ARGS);
					}else {
						throw new CommandParseException(msgUnvalidAdd);
					}
				} catch (NumberFormatException num) {
					System.out.println(num.getMessage());
					throw new CommandParseException(
							"[ERROR]: '" + commandWords[1] + "' || '" + commandWords[2] + "' is not a number");
				}
			}
		return null;
	}

}

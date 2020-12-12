package control.Commands;

import logic.Game;

public class AddVampireCommand extends Command {

	private static final String NAME = "vampire";
	private static final String SHORTCUT = "v";
	private static final String DETAILS = "[v]ampire [<TYPE>] <x> <y>";
	private static final String HELP = "add vampire TYPE position x y";
	private static final int ARGS = 3;
	private static final int ARGSTYPE = 4;
	private int x;
	private int y;
	private String type;
	
	public AddVampireCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
		type = null;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {		
		if(game.addVampire(x, y, type)) return true;
		else return false;			
	}

	@Override
	public Command parse(String[] commandWords) {
		//Si no ha introducido caracteres
		if(commandWords.length > 3 && isNumeric(commandWords[2]) && isNumeric(commandWords[3]) && !isNumeric(commandWords[1])) {
			type = commandWords[1];
			x= Integer.parseInt(commandWords[2]);//Lo convertimos en numero
			y= Integer.parseInt(commandWords[3]);
			//Retornamos dependiendo de los argumentos que habia en el parse
			return parseParamsCommand(commandWords, ARGSTYPE);
		}
		else if(commandWords.length > 2 && isNumeric(commandWords[1]) && isNumeric(commandWords[2]) ) {
			x= Integer.parseInt(commandWords[1]);//Lo convertimos en numero
			y= Integer.parseInt(commandWords[2]);
			return parseParamsCommand(commandWords, ARGS);
		}
		else return parseNoParamsCommand(commandWords);
		
	}

}

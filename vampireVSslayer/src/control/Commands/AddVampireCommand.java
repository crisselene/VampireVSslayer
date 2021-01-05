package control.Commands;

import logic.Game;
import logic.Exceptions.CommandExecuteException;
import logic.Exceptions.CommandParseException;

public class AddVampireCommand extends Command {

	private static final String NAME = "vampire";
	private static final String SHORTCUT = "v";
	private static final String DETAILS = "[v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}";
	private static final String HELP = "add a vampire in position x, y";
	private static final int ARGS = 3;
	private static final int ARGSTYPE = 4;
	private int x;
	private int y;
	private String type;
	String msgUnvalidType= "Unvalid type: ";
	
	public AddVampireCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
		type = null;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {		
		return(game.addVampire(x, y, type));			
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords.length > 2) {
			if(commandWords.length > 3 && isNumeric(commandWords[2]) && isNumeric(commandWords[3]) && !isNumeric(commandWords[1])) {
				type = commandWords[1];
				x= Integer.parseInt(commandWords[2]);//Lo convertimos en numero
				y= Integer.parseInt(commandWords[3]);
				//Retornamos dependiendo de los argumentos que habia en el parse
				return parseParamsCommand(commandWords, ARGSTYPE);
			}else if( isNumeric(commandWords[1]) && isNumeric(commandWords[2]) ) {
				x= Integer.parseInt(commandWords[1]);//Lo convertimos en numero
				y= Integer.parseInt(commandWords[2]);
				return parseParamsCommand(commandWords, ARGS);
			}else throw new CommandParseException("[ERROR]: " + msgUnvalidType + DETAILS);
		}else return null;
		
		//Si no ha introducido caracteres
//		if(commandWords.length > 3 && isNumeric(commandWords[2]) && isNumeric(commandWords[3]) && !isNumeric(commandWords[1])) {
//			type = commandWords[1];
//			x= Integer.parseInt(commandWords[2]);//Lo convertimos en numero
//			y= Integer.parseInt(commandWords[3]);
//			//Retornamos dependiendo de los argumentos que habia en el parse
//			return parseParamsCommand(commandWords, ARGSTYPE);
//		}
//		else if(commandWords.length > 2 && isNumeric(commandWords[1]) && isNumeric(commandWords[2]) ) {
//			x= Integer.parseInt(commandWords[1]);//Lo convertimos en numero
//			y= Integer.parseInt(commandWords[2]);
//			return parseParamsCommand(commandWords, ARGS);
//		}
//		else return null;
//		
	}

}

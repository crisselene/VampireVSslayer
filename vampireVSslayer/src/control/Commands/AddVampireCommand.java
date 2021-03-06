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
	String msgUnvalidArg= "Unvalid argument for add vampire command, number expected: ";
	
	public AddVampireCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
		type = null;
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {		
		try {
			return(game.tryAddVampire(x, y, type));
		} catch (CommandParseException cpe) {
			throw new CommandExecuteException("[ERROR]: "+cpe.getMessage()+": " + DETAILS);
		} catch (CommandExecuteException ex) {
			System.out.println(ex.getMessage());
			throw new CommandExecuteException("[ERROR]: Failed to add this vampire");
		}			
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {

		if (commandWords.length > 2) {
			try {
				if (commandWords.length > 3) { // && isNumeric(commandWords[2]) && isNumeric(commandWords[3]) &&
												// !isNumeric(commandWords[1])) {
					type = commandWords[1];
					x = Integer.parseInt(commandWords[2]);// Lo convertimos en numero
					y = Integer.parseInt(commandWords[3]);
					// Retornamos dependiendo de los argumentos que habia en el parse
					return parseParamsCommand(commandWords, ARGSTYPE);
				} else {// if( isNumeric(commandWords[1]) && isNumeric(commandWords[2]) ) {
					type = commandWords[0];
					x = Integer.parseInt(commandWords[1]);// Lo convertimos en numero
					y = Integer.parseInt(commandWords[2]);
					return parseParamsCommand(commandWords, ARGS);
				} // else throw new CommandParseException("[ERROR]: " + msgUnvalidType + DETAILS);
			} catch (NumberFormatException num) {
				throw new CommandParseException(msgUnvalidArg + DETAILS);
			}

		}
		return null;
	}

}
//		if(commandWords.length > 2) {
//			if(commandWords.length > 3 && isNumeric(commandWords[2]) && isNumeric(commandWords[3]) && !isNumeric(commandWords[1])) {
//				type = commandWords[1];
//				x= Integer.parseInt(commandWords[2]);//Lo convertimos en numero
//				y= Integer.parseInt(commandWords[3]);
//				//Retornamos dependiendo de los argumentos que habia en el parse
//				return parseParamsCommand(commandWords, ARGSTYPE);
//			}else if( isNumeric(commandWords[1]) && isNumeric(commandWords[2]) ) {
//				x= Integer.parseInt(commandWords[1]);//Lo convertimos en numero
//				y= Integer.parseInt(commandWords[2]);
//				return parseParamsCommand(commandWords, ARGS);
//			}else throw new CommandParseException("[ERROR]: " + msgUnvalidType + DETAILS);
//		}
//		return null;
//
//	}

//}

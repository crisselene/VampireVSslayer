package control.Commands;

import logic.Game;

public class AddBloodBankCommand extends Command {
	
	private static final int ARGS=4;
	private int x;
	private int y;
	private int z;

	public AddBloodBankCommand() {
		super("bank", "b", "[b]ank <x> <y> <z>", "add a blood bank");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		boolean creado = game.addBank(x, y, z);
		if(creado) {
			game.update();
			return true;
		}
		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		//Si no ha introducido caracteres
				if(commandWords.length > 3 && isNumeric(commandWords[1])
				&& isNumeric(commandWords[2]) && isNumeric(commandWords[3])) {
					
					x= Integer.parseInt(commandWords[1]);//Lo convertimos en numero
					y= Integer.parseInt(commandWords[2]);
					z= Integer.parseInt(commandWords[3]);
					//Retornamos dependiendo de los argumentos que habia en el parse
					return parseParamsCommand(commandWords, ARGS);
				}
				else return parseNoParamsCommand(commandWords);
	}
}

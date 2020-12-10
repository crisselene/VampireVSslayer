package control.Commands;

import logic.Game;

public class AddCommand extends Command {
	
	private static final int ARGS=3;
	private static final int COSTE = 50;
	private int x;
	private int y;
	
	

	public AddCommand() {
		super("add", "a", "[a]dd <x> <y>", "add a slayer in position x, y");
	}

	@Override
	public boolean execute(Game game) {
		// Añadiriamos slayer, pero tenemos que crear las nuevas listas
		boolean creado = game.addSlayer(x, y, COSTE);
		if(creado) {
			game.update();
			return true;
		}
		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		//Si no ha introducido caracteres
		if(commandWords.length > 2 && isNumeric(commandWords[1]) && isNumeric(commandWords[2])) {
			x= Integer.parseInt(commandWords[1]);//Lo convertimos en numero
			y= Integer.parseInt(commandWords[2]);
			//Retornamos dependiendo de los argumentos que habia en el parse
			return parseParamsCommand(commandWords, ARGS);
		}
		else return parseNoParamsCommand(commandWords);
		
	}
	
    //Metodo para ver si un string es numerico
    public boolean isNumeric(String string)
    {
    	boolean resultado;

        try {
            Integer.parseInt(string);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
 
        return resultado;
    }

}

package control.Commands;

import logic.Game;

public class AddCommand extends Command {
	
	private int x;
	private int y;

	public AddCommand() {
		super("add", "a", "[A]dd", "adds a slayer");
	}

	@Override
	public boolean execute(Game game) {
		// Añadiriamos slayer, pero tenemos que crear las nuevas listas
		System.out.println("Añadimos slayer en la pos : " + x + "  -  " + y);
		game.addSlayer(x , y);
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		//Si no ha introducido caracteres
		if(commandWords.length > 2 && isNumeric(commandWords[1]) && isNumeric(commandWords[2])) {
			x= Integer.parseInt(commandWords[1]);//Lo convertimos en numero
			y= Integer.parseInt(commandWords[2]);
			//Retornamos dependiendo de los argumentos que habia en el parse
			return parseParamsCommand(commandWords);
		}
		else return null;
		
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

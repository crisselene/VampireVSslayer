package control.Commands;

import logic.Game;

public class AddCommand extends Command {

	public AddCommand() {
		super("add", "a", "[A]dd", "adds a slayer");
	}

	@Override
	public boolean execute(Game game) {
		// Añadiriamos slayer, pero tenemos que crear las nuevas listas
		System.out.println("Añadimos slayer en la pos" );
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		//no se como pasarme aqui los argumentos del command
		return null;
	}

}

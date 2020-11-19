package control.Commands;

import logic.Game;

public class HelpCommand extends Command {

	private static String name = "help";
	private static String shortcut = "h";
	private static String details = "[h]elp";
	private static String help = "[h]elp: show this help";
	
	
	
	public HelpCommand() {
		super(name, shortcut, details, help);
	}
	
	@Override
	public boolean execute(Game game) {
		CommandGenerator.recorrerArrayHelp();		
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		if(commandWords[0].equals(name) || commandWords[0].equals(shortcut))
			return this;
		
		return null;
	}

}

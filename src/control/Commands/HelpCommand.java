package control.Commands;

import logic.Game;

public class HelpCommand extends Command {

	private static String name = "help";
	private static String shortcut = "h";
	private static String details = "[h]elp";
	private static String help = "show this help";
	
	
	
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
		
		return parseNoParamsCommand(commandWords);
	}

}

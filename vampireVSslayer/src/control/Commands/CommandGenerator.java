package control.Commands;

public class CommandGenerator{

	private static Command[] availableCommands = {
		
		new HelpCommand(),
		new AddCommand(),
		//new ResetCommand(),
		new ExitCommand(),
		new UpdateCommand()
	};
	
	public static Command parse(String[ ] commandWords) {
		for (Command c: availableCommands) {
			Command parsedCommand = c.parse(commandWords);
			if(parsedCommand != null)
				return parsedCommand;
		}
		
		return null; //quiere decir que el comando es invalido
	}
	
	
	public static void recorrerArrayHelp() {
		for (Command c: availableCommands) {
			System.out.println(c.helpText());
		}
			
		
	}
	
	
}

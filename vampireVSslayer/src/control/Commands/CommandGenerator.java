package control.Commands;

import logic.Exceptions.CommandParseException;

public class CommandGenerator{

	public static final String unknownCommandMsg = "Unknown command";
	private static Command[] availableCommands = {

		new AddCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new ExitCommand(),
		new UpdateCommand(),
		new GarlicPushCommand(),
		new LightFlashCommand(),
		new AddBloodBankCommand(),
		new SuperCoinsCommand(),
		new AddVampireCommand(),		
		new SaveCommand(),
		new SerializeCommand()
	};

	public static Command parse(String[] commandWords) throws CommandParseException {
		try {
			for (Command c: availableCommands) {
				Command parsedCommand = c.parse(commandWords);
				if(parsedCommand != null)
					return parsedCommand;
			}
		}catch(NumberFormatException num) {
			throw new CommandParseException("[ERROR]: " + num.getMessage());
		}catch(CommandParseException comm) {
			throw new CommandParseException("[ERROR]: " + comm.getMessage());
		}
		throw new CommandParseException("[ERROR]: "  +unknownCommandMsg);//***********ese mensaje va en el controler?¿*****
		
		}	
		


	public static void recorrerArrayHelp() {
		System.out.println("Available commands:");
		for (Command c: availableCommands) {
			System.out.println(c.helpText());
		}
		System.out.print("\n");
	}


}

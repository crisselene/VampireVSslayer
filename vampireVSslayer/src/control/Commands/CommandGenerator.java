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
		//new SerializeCommand(),
		new SaveCommand()

	};

	public static Command parse(String[] commandWords) throws CommandParseException {
		try {
			for (Command c: availableCommands) {
				Command parsedCommand = c.parse(commandWords);
				if(parsedCommand != null)
					return parsedCommand;
			}
		}catch(NumberFormatException num) {
		System.out.println("num mal");//***************************************************++
		}catch(CommandParseException comm) {
			throw new CommandParseException("[ERROR]: Command " + comm.getMessage());
		}
		throw new CommandParseException("[ERROR]: "  +unknownCommandMsg);//***********ese mensaje va en el controler?¿*****
		
		}	
		//return null;
		
//		try {
//			for (Command c: availableCommands) {
//				Command parsedCommand = c.parse(commandWords);
//					if(parsedCommand != null) {
//						return parsedCommand;
//					}
//			}
//		}catch(NumberFormatException num) {
//			System.out.println(num.getMessage());
//			throw new CommandParseException("[ERROR] '" + commandWords[1] + "' || '" + commandWords[2]
//					+ "' is not a number");
//		}
//		
		//throw new CommandParseException("[ERROR]: "  +unknownCommandMsg);//***********ese mensaje va en el controler?¿*****


	public static void recorrerArrayHelp() {
		System.out.println("Available commands:");
		for (Command c: availableCommands) {
			System.out.println(c.helpText());
		}
		System.out.print("\n");
	}


}

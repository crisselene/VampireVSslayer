package control.Commands;

import logic.Exceptions.CommandParseException;

public class CommandGenerator{

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
		new AddVampireCommand()

	};

	public static Command parse(String[] commandWords) throws CommandParseException {
		for (Command c: availableCommands) {
			Command parsedCommand = c.parse(commandWords);
			if(parsedCommand != null)
				return parsedCommand;
		}

		throw new CommandParseException("[ERROR]: " /*+ unknownCommandMsg*/);//**********
		//return null; //quiere decir que el comando es invalido ------
	}


	public static void recorrerArrayHelp() {
		System.out.println("Available commands:");
		for (Command c: availableCommands) {
			System.out.println(c.helpText());
		}
		System.out.print("\n");
	}


}

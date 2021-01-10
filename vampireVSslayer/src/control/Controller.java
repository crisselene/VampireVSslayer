package control;

import java.util.Scanner;
import control.Commands.Command;
import control.Commands.CommandGenerator;
import logic.Game;
import logic.Exceptions.CommandExecuteException;
import logic.Exceptions.GameException;

public class Controller {

	public final String prompt = "Command > ";

	private Game game;
	private Scanner scanner;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
	}

	public void printGame() {
		System.out.println(game);
	}
    
	public void run() {
		boolean refreshDisplay = true;

		while (!game.isFinished()) {

			if (refreshDisplay)
				printGame();
			refreshDisplay = false;

			System.out.println(prompt);
			String s = scanner.nextLine();
			String[] parameters = s.toLowerCase().trim().split(" ");
			System.out.println("[DEBUG] Executing: " + s);
			try {
				Command command = CommandGenerator.parse(parameters);
				refreshDisplay = command.execute(game);
			} catch (GameException ex) {
				System.out.format(ex.getMessage() + "%n%n");
			}
		}
		if (refreshDisplay) {
			int ciclosFinales = game.getCiclos() - 1;
			game.setCiclos(ciclosFinales);
			printGame();
		}
		System.out.println("[GAME OVER] " + game.getWinnerMessage());

	}

}
package control;

import java.util.Scanner;

import logic.Game;
import logic.Level;
import logic.VampireList;
import objetos.Vampiro;

public class Controller {

	
	public final String prompt = "Command > ";
	public static final String helpMsg = String.format(
			"Available commands:%n" +
			"[a]dd <x> <y>: add a slayer in position x, y%n" +
			"[h]elp: show this help%n" + 
			"[r]eset: reset game%n" + 
			"[e]xit: exit game%n"+ 
			"[n]one | []: update%n");
	
	public static final String unknownCommandMsg = String.format("Unknown command");
	public static final String invalidCommandMsg = String.format("Invalid command");
	public static final String invalidPositionMsg = String.format("Invalid position");

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void  printGame() {
   	 System.out.println(game);
   }
    
    public void run(Level level, VampireList vampireList) {
		// TODO fill your code
    	
    	GameObjectBoard board = new GameObjectBoard();
    	//TODO FORMALIZAR si quiere jugar le damos al 1 y si no al 0
    	int userAnswer = 1;
    	while(userAnswer!= 0) {
    		System.out.println("¿Quieres jugar?");
    		Scanner input = new Scanner (System.in); 
    		userAnswer = input.nextInt(); 
	        if (userAnswer == 1) {
	        	
	        	//crear array vampiros
	        	//double numVamp = level.numberOfVampires();
	        	//vampireList.arrayVampNivel(numVamp);
	        	
	        	//intentar añadir vampiro en la partida
	        	board.addVampire(level, vampireList);
	        	printGame();
	        	
	        }else {
        	//TODO FORMALIZAR
        	System.out.println("Juego finalizado");
        	System.exit(0);
	        }
    	
    	}
    }

}


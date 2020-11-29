package control;

import java.util.Scanner;

import control.Commands.Command;
import control.Commands.CommandGenerator;
import logic.Game;

public class Controller {
	
	public final String prompt = "Command > ";
	public static final String unknownCommandMsg ="Unknown command";

    private Game game;
    private Scanner scanner;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void  printGame() {
    	//Descomentar lo de abajo cuando tengamos bien implementado el board, para no tener que cambiarlo cada dos por tres
    	//System.out.println(game.infoPartida());
    	System.out.println(game);
   }
    
    public void run() {
	    	boolean refreshDisplay = true;

	    while (!game.isFinished()){
	    		
        		 if (refreshDisplay) printGame();
        		 refreshDisplay = false;
        		 
			  System.out.println(prompt);	
			  String s = scanner.nextLine();
			  String[] parameters = s.toLowerCase().trim().split(" ");
			  System.out.println("[DEBUG] Executing: " + s);
		      Command command = CommandGenerator.parse(parameters);
		      if (command != null) { 
		    	  		refreshDisplay = command.execute(game);
		    	  		//game.update();//ACTUALIZAR CREACION DE VAMPIROS******
		       } 
		       else {
		    	   		System.out.println("[ERROR]: "+ unknownCommandMsg);
		       }
		}
        	if (refreshDisplay) {	
        		 printGame();
        	}
    		System.out.println ("[Game over] " + game.getWinnerMessage());

    }

}
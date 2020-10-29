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
    	
    	GameObjectBoard board = new GameObjectBoard(game.getSeed());
    	boolean salir = false;
    
    	while(!game.isFinished(salir)) {
    	     	
	        	//crear array vampiros
	        	//double numVamp = level.numberOfVampires();
	        	//vampireList.arrayVampNivel(numVamp);
	        	
	        	//intentar añadir vampiro en la partida
	        	board.addVampire(level, vampireList);
	        	printGame();
	        	
	        	
	        	//Pedimos el comando al usuario
	        	System.out.print(prompt);
	        	String cmd = scanner.nextLine();
	        	String[] cmdParts = cmd.split(" ");//Separa el string
	        	
	        	//Dependiendo de lo que dijo realizamos una accion u otra
	        	switch(cmdParts[0].toLowerCase()) {
	        	//Todos los casos de ayuda
	        	case "h":
	        	case "help":
	        		System.out.println(helpMsg);
	        	break;
	        	
	        	//Casos de añadir slayer
	        	case "a":
	        	case "add":
	        		//Si el comando tiene tres partes como debe ser " add <x> <y> "
	        		if(cmdParts.length == 3) {
	       
	        			//Si no ha introducido caracteres
	        			if(isNumeric(cmdParts[1]) && isNumeric(cmdParts[2])) {
	        				int posx= Integer.parseInt(cmdParts[1]);//pasa de int a string
	        				int posy= Integer.parseInt(cmdParts[2]);
	        				//Si esta dentro del tablero
	        				if(posx >= 0 && posx < game.getLevelDimX() && posy >= 0 && posy < game.getLevelDimY())
							{	
	        					board.addSlayer(posx, posy);//Añadimos un slayer
	        				}
	        				else
	        				{
	        					//Como no esta dentro del tablero no es valido el comando
	        					System.out.println(invalidCommandMsg);
	        				}
	        			}//If isNumeric
	        			else
	        			{
	        				System.out.println(invalidCommandMsg);
	        			}
	        		}//if cmdParts.lenght != 2
	        		else 
	        		{
	        			System.out.println(invalidCommandMsg);
	        		}
	        		
	        	break; //Casos de añadir slayer
	        	//Casos siguiente turno
	        	case "n":
	        	case "none":
	        	case "":
	        		//Acutalizaria el juego simplemente
	        		//Implementar todo lo que pasa automaticamente sin accion del usuario aqui
	        		game.actualizarPartida(board, vampireList);
	        	break;
	        	//Caso exit
	        	case "e":
	        	case "exit":
	        		//Salimos del juego
	        		salir = true;
	        	break;        	
	        	}//switch
	        		        	   	
	  	}//while game.isFinished
    }
    //Metodo para ver si un string es numerico
    public boolean isNumeric(String string)
    {
    	boolean resultado;

        try {
            Integer.parseInt(string);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
 
        return resultado;
    }
    
}


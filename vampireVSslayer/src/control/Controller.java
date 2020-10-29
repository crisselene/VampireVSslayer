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
	        	
	        	
	        	//Pedimos el comando al usuario
	        	System.out.print(prompt);
	        	String cmd = scanner.next();
	        	
	        	//Dependiendo de lo que dijo realizamos una accion u otra
	        	switch(cmd) {
	        	//Todos los casos de ayuda
	        	case "h":
	        	case "help":
	        	case "H":
	        	case "Help":
	        		System.out.println(helpMsg);
	        	break;
	        	//Casos de añadir slayer
	        	case "a":
	        	case "add":
	        	case "A":
	        	case "Add":
	        		String ayuda= scanner.nextLine();
	        		//Si no es comando vacio
	        		if(ayuda != "") {
	       
	        			//Si no ha introducido caracteres
	        			if(Character.isDigit(ayuda.charAt(1)) && Character.isDigit(ayuda.charAt(3))) {
	        				int posx= Integer.parseInt(Character.toString(ayuda.charAt(1)));//pasa de char a string y a int en la misma linea
	        				int posy= Integer.parseInt(Character.toString(ayuda.charAt(3)));
	        				//Si esta dentro del tablero
	        				if(posx >= 0 && posx < game.getLevelDimX() && posy >= 0 && posy < game.getLevelDimY())
							{	
	        					//Si el jugador tiene monedas suficientes
	        					if(board.getPlayer().getMonedas() >= 50) {
	        						//Restamos 50
	        						board.getPlayer().setMonedas(board.getPlayer().getMonedas()-50);
	        						//Añadimos el vampiro a la lista
	        						board.crearSlayer(posx,posy);
	        					}
	        					else //En caso contrario
	        					{
	        						//Mostrar que no tiene monedas suficientes
	        						System.out.println("No de dispone de suficientes monedas");
	        					}
	        					
							}//If esta dentro de rango
	        			}//If is,digit
	        			else
	        			{
	        				System.out.println(invalidCommandMsg);
	        			}
	        		}//if ayuda != ""
	        		else 
	        		{
	        			System.out.println(invalidCommandMsg);
	        		}
	        		
	        	break; //Casos de añadir slayer
	        	default:
	        	break;        	
	        	}//switch
	        	//Vemos si el jugador recibe mondeas o no aleatoriamente
	        	board.getPlayer().ganaMonedas(game.getSeed());
	        		        	
	        }else {
        	//TODO FORMALIZAR
        	System.out.println("Juego finalizado");
        	System.exit(0);
	        }
    	
	  	}//while userAnswer
    }

}


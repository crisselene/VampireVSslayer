package control;

import java.util.Random;
import java.util.Scanner;
import logic.Game;
import logic.Level;
import logic.SlayerList;
import logic.VampireList;

public class Controller {

	
	public final String prompt = "Command > \n";
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
    private VampireList vampireList;
    private Scanner scanner;
    private SlayerList slayerList;
    private Random random;
    
    public Controller(Game game, Scanner scanner) {
	    this.game = game;
	    this.scanner = scanner;
    }
    
    public void  printGame() {
   	 System.out.println(game);
   }
    
    public void run(Level level, VampireList vampireList) {
		
    	random = new Random(game.getSeed());
    	GameObjectBoard board = new GameObjectBoard();
    	boolean salir = false;
    	int numCiclos = 0;
    	int opcion = 1;
    	
    	while(!game.isFinished(salir, board, numCiclos)) {    	     	
    		
    		//mostramos la informacion de la partida
    		if( opcion == 1 || opcion == 3)  {
    			game.infoPartida(board,level, numCiclos);
    	        printGame();	
    		}
	        
	        //se pide al usuario acción
	        opcion = opcionUsuario(board, vampireList);
	        
	        //Dependiendo de lo que eligiera el usurio ocurrira una accion u otra
	        if(opcion == 1) {
	        	
	        	//El juego se desarrolla normalmente
	        	game.actualizarPartida(board, vampireList, random);
	        	game.attack(board, vampireList, slayerList);
	        	
	        	
	        	//añadir vampiros
	        	board.addVampire(level, vampireList, random);
	        	//Eliminar muertos
	        	game.buscarMuertos(board, vampireList);	      
	        	//Numero de ciclos aumenta
	        	numCiclos++;
	        }
	        else if(opcion == 3) {
	        	//Ha habido reset
	        	numCiclos = 0;
	        }
	        else if(opcion == 2) {
	        	//Ha habido exit
	        	System.out.println("No ha ganado nadie");
	        	salir = true;
	        }	          	
        	
	  	}
    }
    
    public int opcionUsuario(GameObjectBoard board, VampireList vampireList) {
    	int opcion = 0;
    	//La variable opcion tendra cuatro valores, con el 0 
    	//quiere decir que no se actualizara el juego, con 1 que si lo hara
    	//con el 2 que ha decidido salir del juego, y el 3 que ha hecho reset
    	
    	
    	//Pedimos el comando al usuario
        System.out.print(prompt);
        String cmd = scanner.nextLine();
        String[] cmdParts = cmd.split(" ");//Separa el string
        //Dependiendo de lo que dijo realizamos una accion u otra
        switch(cmdParts[0].toLowerCase()) {
        //Casos de resetear partida
        case "r":
        case "reset":
        	random = new Random(game.getSeed());
        	game.reset(board, vampireList, random);
        	opcion = 3;
        break;	
        //Todos los casos de ayuda
        case "h":
        case "help":
        	System.out.println(helpMsg);
        	opcion = 0;
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
       				//Si esta dentro del tablero y no coincide con algun objeto
       				if(posx >= 0 && posx < game.getLevelDimX() && posy >= 0 && posy < game.getLevelDimY() && !SlayerList.noCoincide(posx, posy)
       						&& posx != game.getLevelDimX() - 1)
					{	
       					boolean anadido = board.addSlayer(posx, posy);//Añadimos un slayer       					
       					if(anadido) {
       						opcion = 1;
       					}
       					else {
       						opcion = 0;       						
       					}
        			}
        			else
        			{
        				//Como no esta dentro del tablero no es valido el comando
       					System.out.println(invalidPositionMsg);       					
       					opcion = 0;
       				}
       			}//If isNumeric
       			else
       			{
        				System.out.println(invalidCommandMsg);
        				opcion = 0;
        		}
        	}//if cmdParts.lenght != 2
        	else 
       		{
       			System.out.println(invalidCommandMsg);
       			opcion= 0 ;
       		}
       		//Actualiza la partida
       		
       	break; //Casos de añadir slayer
       	//Casos siguiente turno
       	case "n":
       	case "none":
    	case "":
       		//Acutalizaria el juego simplemente
       		//Implementar todo lo que pasa automaticamente sin accion del usuario aqui
    		opcion = 1;
       	break;
       	//Caso exit
       	case "e":
    	case "exit":
       		//Salimos del juego
       		opcion = 2;
       	break;
       	default://Cualquier caso que no sea valido
       		System.out.println(unknownCommandMsg);
       		opcion= 0;
       	break;
       	}//switch 
        //Si no escribio nada cmdPArts es vacio y hay que decir unknown command
        return opcion;
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


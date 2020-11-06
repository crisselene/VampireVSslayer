package logic;

import control.Controller;
import control.GameObjectBoard;
import objetos.Vampiro;
import view.GamePrinter;

public class Game {
	
	private long seed;
	private Level level;
	private GamePrinter printer;
	

	public Game(Long seed, Level level) {
		printer= new GamePrinter(this,level.getDimx(),level.getDimy());
		this.level=level;
		this.seed = seed;
	}
	
	public boolean isFinished(boolean salir) {
		if (salir) {
			System.out.println("La partida ha terminado");
			System.exit(0);
			return true;
		}
		else if(VampireList.llegoAlFinal()) {
			System.out.println("GAME OVER");
			System.exit(1);
			return true;
		}
		return false;
	}
	
	public String toString() {
		return printer.toString();
	}

	public String getPositionToString(int y, int x) {
		//Si queremos un slayer
		Vampiro arrayVamp[] = VampireList.getArrayVamp();
		for (int i = 0 ; i < SlayerList.getNumSlayers() ; i++)
		{
			if (SlayerList.getSlayer(i).getPosx()== x && SlayerList.getSlayer(i).getPosy()== y) {
				return SlayerList.getSlayer(i).toString();
			}
		}
		for (int i = 0 ; i < VampireList.getLongitud() ; i++)
		{
			if (arrayVamp[i].getPosx() == x && arrayVamp[i].getPosy() == y) {
				return arrayVamp[i].toString();
			}
		}
		return "-";
	}
	public int getLevelDimX() {
		return level.getDimx();
	}
	public int getLevelDimY() {
		return level.getDimy();
	}
	
	public long getSeed() {
		return seed;
	}

	//Acutaliza toda la partida
	public void actualizarPartida(GameObjectBoard  board, VampireList vampireList) {
		//Vemos si el jugador recibe mondeas o no aleatoriamente
    	board.recibeMonedas();
    	//si hay vampiros, los vampiros avanzan
    	if(vampireList.getLongitud()!=0) {
    	//board.avanzarVampire(vampireList);
    		Vampiro.avanza(vampireList);
    	
    	//Añadir cualquier movimiento de los vampiros
    	}
	}
	

	public void infoPartida(GameObjectBoard board,VampireList vampire,Level level, int numCiclos) {
		//Mostramos todda la info necesaria
		System.out.println("Number of cycles: " + numCiclos +"\n"
						+ "Coins: " + board.getMonedas() + "\n"
						//números de vampiros restantes, son los que faltan por meter en el array (casteado a int)
						+ "Remaning vampires: " + (int)(level.numberOfVampires() - vampire.getLongitud())+ "\n"
						+ "Vampires on board: " + vampire.getLongitud());
	}
	
	//resetea la partida
	public void reset(GameObjectBoard board, VampireList vampireList) {
		board.reset(getSeed());
		vampireList.reset();		
	}

	public void attack(GameObjectBoard board, VampireList vampireList, SlayerList slayerList) {
		board.slayerAttack();
		//Aqui el de los vampiros
		//los vampiros commprueban si pueden atacar
    	board.VampireAttack(vampireList, slayerList);
	}

	public void buscarMuertos(GameObjectBoard board, VampireList vampireList) {
		board.buscarSlayers();
		vampireList.buscarVampiro();
	}
}

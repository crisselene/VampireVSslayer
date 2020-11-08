package logic;

import java.util.Random;
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
	
	public boolean isFinished(boolean salir, GameObjectBoard board, int numCiclos) {
		if (salir) {
			System.out.println("La partida ha terminado");
			System.exit(0);
			return true;
		}
		else if(VampireList.llegoAlFinal()) {
			infoPartida(board, level, numCiclos - 1);
			System.out.println(this);
			System.out.println("GAME OVER");
			System.exit(1);
			return true;
		}
		else if(level.numberOfVampires() == VampireList.getVampSalidos() && VampireList.getLongitud() == 0) {
			infoPartida(board, level, numCiclos - 1);
			System.out.println(this);
			System.out.println("ENHORABUENA, HAS MATADO A TODOS LOS VAMPIROS");
			System.exit(2);
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
		return " ";
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
	public void actualizarPartida(GameObjectBoard  board, VampireList vampireList, Random random) {
		//Vemos si el jugador recibe mondeas o no aleatoriamente
    	board.recibeMonedas(random);
    	//si hay vampiros, los vampiros avanzan
    	if(VampireList.getLongitud()!=0) {
    	Vampiro.avanza(vampireList);    	
    	}
	}
	

	public void infoPartida(GameObjectBoard board,Level level, int numCiclos) {
		//Mostramos todda la info necesaria
		System.out.println("Number of cycles: " + numCiclos +"\n"
						+ "Coins: " + board.getMonedas() + "\n"
						//números de vampiros restantes, son los que faltan por meter en el array (casteado a int)
						+ "Remaining vampires: " + (int)(level.numberOfVampires() - VampireList.getVampSalidos())+ "\n"
						+ "Vampires on the board: " + VampireList.getLongitud());
	}
	
	//resetea la partida
	public void reset(GameObjectBoard board, VampireList vampireList, Random random) {
		board.reset();
		VampireList.reset();		
	}

	public void attack(GameObjectBoard board, VampireList vampireList, SlayerList slayerList) {
		board.slayerAttack();
		board.VampireAttack(vampireList, slayerList);
	}

	public void buscarMuertos(GameObjectBoard board, VampireList vampireList) {
		board.buscarSlayers();
		VampireList.buscarVampiro();
	}
}

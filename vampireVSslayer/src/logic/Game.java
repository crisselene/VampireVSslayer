package logic;

import java.util.Random;
import control.GameObjectBoard;
import objetos.Player;
import objetos.Slayer;
import objetos.Vampiro;
import view.GamePrinter;
import view.IPrintable;

public class Game implements IPrintable {
	
	private long seed;
	private Level level;
	private GamePrinter printer;
	private GameObject gameObj;
	private GameObjectList obList;
	private boolean userExit;
	private Player player;
	

	public Game(Long seed, Level level) {
		printer= new GamePrinter(this,level.getDimx(),level.getDimy());
		this.level=level;
		this.seed = seed;
		userExit = false;
		obList = new GameObjectList();
		player = new Player();
	}
	
	public boolean isFinished() {
		//El return debe ser algo como vamp.llego() || vamp.noQuedan() || userExit
		return userExit;
	}
	
	public String toString() {
		return printer.toString();
	}

	public String getPositionToString(int x, int y) {
		return obList.toString(x, y);
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

	public String getWinnerMessage() {
		if(userExit) {
			return "Bye";
		}
		else {
			return "Los vampiros llegaron al final";
		}
	}
	
	public void doExit() {
		userExit = true;
	}

	public boolean addSlayer(int x, int y) {
		if(player.tieneMonedas() && !obList.buscarObjeto(x, y)) {
			Slayer slayer = new Slayer(x, y);
			addObject(slayer);
			player.restarMonedas();
			return true;
		}
		return false;
	}

	public void addObject(GameObject objeto) {
		obList.anadirObjeto(objeto);
	}
		
	@Override
	public String getInfo() {
		String info = ("Monedas = " + player.getMonedas());
		return info;
	}

	public void doReset() {
		obList.resetList();
		
	}

}

package logic;

import java.util.Random;
import objetos.Player;
import objetos.Slayer;
import objetos.Vampiro;
import view.GamePrinter;
import view.IPrintable;

public class Game implements IPrintable {
	
	private long seed;
	private Level level;
	private GamePrinter printer;
	private GameObjectBoard board;
	private boolean userExit;
	private Player player;
	private int ciclos;
	private Random random;
	

	public Game(Long seed, Level level) {
		printer= new GamePrinter(this,level.getDimx(),level.getDimy());
		this.level=level;
		this.seed = seed;
		userExit = false;
		player = new Player();
		ciclos = 0;
		board = new GameObjectBoard(level);
		random = new Random(seed);
	}
	
	public boolean isFinished() {
		//El return debe ser algo como vamp.llego() || vamp.noQuedan() || userExit
		return userExit || llegoFinal() || userVictory();
	}
	
	private boolean userVictory() {		
		return board.userVictory();
	}

	private boolean llegoFinal() {		
		return board.llegoFinal();
	}

	public String toString() {
		return printer.toString();
	}

	public String getPositionToString(int x, int y) {
		return board.toString(x, y);
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
	
	public String getWinnerMessage() {
		if(userExit) {
			return "Nobody wins...";
		}
		else if (llegoFinal()) {
			return "Los vampiros llegaron al final";
		}
		else return "Has matado a todos los vampiros!!!";
	}
	
	public void doExit() {
		userExit = true;
	}

	public boolean addSlayer(int x, int y) {
		if(board.dentroTablero(y, x)) {
			
			
			if(player.tieneMonedas()) {
				Slayer slayer = new Slayer(x, y, this);
				if(board.addSlayer(slayer, y, x)) {
					player.restarMonedas();
					return true;
				}
			}else {
				System.out.println("Not enough coins");
				return false;
			}
		}
		System.out.println("Invalid position");
		return false;
	}
		
	@Override
	public String getInfo() {
		String info = ("Number of cycles: " + ciclos + "\n" +
						"Coins: " + player.getMonedas() + "\n"+
						"Remaining vampires: " + board.getVampRestantes() + "\n" +
						"Vampires on the board: " + board.vampEnTablero() + "\n");
		return info;
	}

	public void doReset() {
		board = new GameObjectBoard(level);
		ciclos = 0;
		player = new Player();
	}

	public void update() {
		ciclos ++;
		player.ganaMonedas(random.nextFloat());
		board.move();
		board.attack();
		this.crearVampiro();
		board.removeDead();
		
	}

	private void crearVampiro() {
		int columna = (level.getDimx() - 1);
		int fila = board.addVampire(columna, random.nextFloat(), random.nextInt(level.getDimy()));
		if(fila != -1) {
			Vampiro v = new Vampiro(columna, fila, this);
			board.addObject(v);
		}
		
		
	}

	public IAttack getAttackableInPosition(int posx, int posy) {
		GameObject obj;
		obj = board.getAttackableInPosition(posx,  posy);
		return obj;
	}
	
	public IAttack getAttackableInLine(int posy) {
		GameObject obj;
		obj = board.getAttackableInLine(posy);
		return obj;
	}


}

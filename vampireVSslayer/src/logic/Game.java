package logic;

import java.util.Random;

import objetos.Dracula;
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

	public int getCiclos() {
		return ciclos;
	}

	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
	}

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
			return "Vampires win!";
		}
		else {
			return "Player wins";
		}
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
						if(Dracula.isDraculaOnBoard()) {
							info = info + ("Dracula is alive\n");
						}
		return info;
	}

	public void doReset() {
		board = new GameObjectBoard(level);
		ciclos = 0;
		player = new Player();
	}

	public void update() {
		player.ganaMonedas(random.nextFloat());
		board.move();
		board.attack();
		//this.crearVampiro();
		this.creacionVampiros();
		board.removeDead();
		ciclos++;
		
	}

	//método que crea todos los tipos de vampiros con la misma frecuencia random
	private void creacionVampiros() {
		double randFreq = random.nextDouble();
		crearVampiro(randFreq);
		crearDracula(randFreq);
		//TODO: anadir explosivos
		
	}

	private void crearVampiro(double randFreq) {
		int columna = (level.getDimx() - 1);
		int fila = board.addVampire(columna, randFreq , random);
		if(fila != -1) {
			Vampiro v = new Vampiro(columna, fila, this);
			board.addObject(v);
		}
	}
	
	private void crearDracula(double randFreq) {
		int columna = (level.getDimx() - 1);
		int fila = board.addVampire(columna, randFreq , random);
		if(fila != -1) {
			Dracula d = new Dracula(columna, fila, this);
			if(!Dracula.isDraculaOnBoard()) {
				board.addObject(d); 
				Dracula.setDraculaOnBoard(true);
			}
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

package logic;

import java.util.Random;

import objetos.BloodBank;
import objetos.Dracula;
import objetos.ExplosiveVampire;
import objetos.Player;
import objetos.Slayer;
import objetos.Vampiro;
import view.GamePrinter;
import view.IPrintable;

public class Game implements IPrintable {

	private static final int COSTE_SLAYER = 50;
	private static final int COSTE_GARLIC = 10;
	private static final int COSTE_LIGHT = 50;
	private static final int SUPER_MONEDAS = 1000;
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

			if(player.tieneMonedas(COSTE_SLAYER)) {
				if(!board.buscarObjeto(x, y)) {
					Slayer slayer = new Slayer(x, y, this);
					board.addObject(slayer);
					player.restarMonedas(COSTE_SLAYER);
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
		this.creacionVampiros();
		board.removeDead();
		ciclos++;

	}

	//método que crea todos los tipos de vampiros con la misma frecuencia random
	private void creacionVampiros() {
		double randFreq = random.nextDouble();
		crearVampiro(randFreq);
		crearDracula(randFreq);
		crearExplosivo(randFreq);

	}

	private void crearExplosivo(double randFreq) {
		int columna = (level.getDimx() - 1);
		int fila = board.addVampire(columna, randFreq , random);
		if(fila != -1) {
			ExplosiveVampire ex = new ExplosiveVampire(columna, fila, this);
			board.addObject(ex);
		}
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

	public boolean addBank(int x, int y, int z) {

		if(board.dentroTablero(y, x)) {

			if(player.tieneMonedas(z)) {				
				if(board.buscarObjeto(x, y)) {
					BloodBank bank = new BloodBank(x, y, z, this);
					board.addObject(bank);
					player.restarMonedas(z);
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

	public void reintegroBanco(int ganancia) {
		//Añade las monedas del banco
		player.reintegroBanco(ganancia);
	}

	public boolean pushVampires() {
		if(player.tieneMonedas(COSTE_GARLIC)) {
			board.pushVampires();
			return true;
		}
		System.out.println("Not enough coins");
		return false;

	}

	public boolean buscarObjeto(int posx, int posy) {
		return board.buscarObjeto(posx, posy);
	}

	public boolean estaAlFinal(int posx) {
		if(posx == level.getDimx() - 1) {
			return true;
		}
		return false;
	}

	public boolean lightVampires() {
		if(player.tieneMonedas(COSTE_LIGHT)) {
			board.lightVampires();
			return true;
		}
		System.out.println("Not enough coins");
		return false;
	}

	public void superMonedas() {
		player.reintegroBanco(SUPER_MONEDAS);
		//Le hacemos el reintegro especial
	}

	public boolean addVampire(int x, int y, String type) {

		if(board.dentroTablero(y, x)) {
			if(!board.buscarObjeto(x, y)){
				if(type.equals("d")) {
					Dracula dracula = new Dracula(x, y, this);
					board.addObject(dracula);
				}
				else if(type.equals("e")) {
					ExplosiveVampire ev = new ExplosiveVampire(x, y, this);
					board.addObject(ev);
				}
				else {
					Vampiro vampire = new Vampiro(x,y,this);
					board.addObject(vampire);
				}
				return true;//Porque en todos los casos se habra añadido el vampiro
			}
			else {
				System.out.println("Other object in this position");
				return false;
			}

		}
		else {
		System.out.println("Invalid position");
		return false;
		}

	}



}

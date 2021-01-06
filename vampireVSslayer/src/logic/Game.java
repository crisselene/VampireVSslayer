package logic;

import java.util.Random;

import logic.Exceptions.CommandExecuteException;
import logic.Exceptions.DraculaIsAliveException;
import logic.Exceptions.NoMoreVampiresException;
import logic.Exceptions.NotEnoughCoinsException;
import logic.Exceptions.UnvalidPositionException;
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
	private static final String INVALID_POSITION= "[ERROR]: Unvalid position";
	private static final String NOT_COINS= "[ERROR]: Defender cost is 50: Not enough coins";
	private static final String DRACULA_ALIVE = "[ERROR]: Dracula is alive";
	private static final String NO_VAMP_LEFT = "[ERROR]: No more remaining vampires left";
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
		return userExit || llegoFinal() || userVictory();
	}

	private boolean userVictory() {
		return board.userVictory();
	}

	private boolean llegoFinal() {
		return Vampiro.llegoPrincipio;
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

	public boolean addSlayer(int x, int y) throws UnvalidPositionException, NotEnoughCoinsException{
		if(board.dentroTablero(y, x) && !board.buscarObjeto(x, y)) {

			if(player.tieneMonedas(COSTE_SLAYER)) {				
				Slayer slayer = new Slayer(x, y, this);
				board.addObject(slayer);
				player.restarMonedas(COSTE_SLAYER);
				return true;
				
			}else throw new NotEnoughCoinsException(NOT_COINS);
		}
		throw new UnvalidPositionException("[ERROR]: Position (" + x + ", "+ y + "): "+ INVALID_POSITION);
	}

	@Override
	public String getInfo() {
		String info = ("Number of cycles: " + ciclos + "\n" +
						"Coins: " + player.getMonedas() + "\n"+
						"Remaining vampires: " + board.getVampRestantes() + "\n" +
						"Vampires on the board: " + board.getVampEnTablero() + "\n");
		//draculaOnBoard es una variable static a la que se puede 
		//acceder desde game sin romper encapsulamiento
						if(Dracula.draculaOnBoard) {
							info = info + ("Dracula is alive\n");
						}
		return info;
	}

	public void doReset() {
		board = new GameObjectBoard(level);
		ciclos = 0;
		player = new Player();
		Dracula.draculaOnBoard=false;
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
		if(board.getVampRestantes() > 0) crearVampiro();
		if(board.getVampRestantes() > 0 && !Dracula.draculaOnBoard) crearDracula();		
		if(board.getVampRestantes() > 0) crearExplosivo();	
	}

	private void crearExplosivo() {
		int columna = (level.getDimx() - 1);
		int fila = board.addVampire(columna, random.nextDouble() , random);
		if(fila != -1) {
			ExplosiveVampire ex = new ExplosiveVampire(columna, fila, this);
			board.addObject(ex);
		}
	}

	private void crearVampiro() {
		int columna = (level.getDimx() - 1);
		int fila = board.addVampire(columna, random.nextDouble() , random);
		if(fila != -1) {
			Vampiro v = new Vampiro(columna, fila, this);
			board.addObject(v); 
		}
	}

	private void crearDracula() {
		
		int columna = (level.getDimx() - 1);
		int fila = board.addVampire(columna, random.nextDouble() , random);
		if(fila != -1) {			
			Dracula d = new Dracula(columna, fila, this);
			board.addObject(d);
			//Dracula.draculaOnBoard=true;
		}
		
	}

	public IAttack getAttackableInPosition(int posx, int posy) {
		GameObject obj;
		obj = board.getAttackableInPosition(posx,  posy);
		return obj;
	}

//	public IAttack getAttackableInLine(int posy) {
//		GameObject obj;
//		obj = board.getAttackableInLine(posy);
//		return obj;
//	}

	public boolean addBank(int x, int y, int z) throws NotEnoughCoinsException {

		if(board.dentroTablero(y, x)) {

			if(!board.buscarObjeto(x, y)) {
				if(player.tieneMonedas(z)) {				
					BloodBank bank = new BloodBank(x, y, z, this);
					board.addObject(bank);
					player.restarMonedas(z);
					return true;
				}else {
					throw new NotEnoughCoinsException("[ERROR]: no hay monedas suficientes");
				}
			}else {
				System.out.println(INVALID_POSITION);
				return false;
			}
		}else{
			System.out.println(INVALID_POSITION);
			return false;
		}
		

	}

	public void reintegroBanco(int ganancia) {
		//Añade las monedas del banco
		player.reintegroBanco(ganancia);
	}

	public boolean pushVampires() throws NotEnoughCoinsException {
		if(player.tieneMonedas(COSTE_GARLIC)) {
			board.pushVampires();
			player.restarMonedas(COSTE_GARLIC);
			return true;
		}
		throw new NotEnoughCoinsException("[ERROR]: no hay monedas suficientes, coste: " + COSTE_GARLIC);
//		System.out.println(NOT_COINS);
//		return false;

	}

	public boolean buscarObjeto(int posx, int posy) {
		return board.buscarObjeto(posx, posy);
	}

//	public boolean estaAlFinal(int posx) {
//			return posx == level.getDimx() - 1; **********************SE NECESITA? ******************+++
//	}

	public boolean lightVampires() throws NotEnoughCoinsException {
		if(player.tieneMonedas(COSTE_LIGHT)) {
			board.lightVampires();
			player.restarMonedas(COSTE_LIGHT);
			return true;
		}
		throw new NotEnoughCoinsException("[ERROR]: no hay monedas suficientes, coste: " + COSTE_LIGHT);
//		System.out.println(NOT_COINS);
//		return false;
	}

	public void superMonedas() {
		player.reintegroBanco(SUPER_MONEDAS);
		//Le hacemos el reintegro especial
	}

	
	//add vampire de comando AddVampireCommand
	public boolean tryAddVampire(int x, int y, String type) throws CommandExecuteException {
		if (board.getVampRestantes() > 0) {
			if (board.dentroTablero(y, x)) {// ****************+
				if (!board.buscarObjeto(x, y)) {
					if (type != null) {
						if (type.equals("d")) {
							if (Dracula.draculaOnBoard == false) {
								Dracula dracula = new Dracula(x, y, this);
								board.addObject(dracula);
								Dracula.draculaOnBoard = true;
							} else
								throw new DraculaIsAliveException("[ERROR]: Dracula está vivo");
							// System.out.println(DRACULA_ALIVE);
							// return false;
						} else if (type.equals("e")) {
							ExplosiveVampire ev = new ExplosiveVampire(x, y, this);
							board.addObject(ev);
						} else if (type.equals("")) {
							Vampiro vampire = new Vampiro(x, y, this);
							board.addObject(vampire);
							/***********/
						} else
							throw new CommandExecuteException(
									"[ERROR]: Unvalid type: [v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}");
					} else {
						Vampiro vampire = new Vampiro(x, y, this);
						board.addObject(vampire);
					}
					board.reducirVampirosRestantes();
					board.aumentarVampirosTablero();
					return true;// Porque en todos los casos se habra añadido el vampiro
				} else {
					throw new UnvalidPositionException("Unvalid position");
				}
			} else {
				throw new UnvalidPositionException("Unvalid position");
			}
		} else {
			throw new NoMoreVampiresException("[ERROR] No se pueden anadir mas vampiros");
		}
	}

	public void reducirVampirosTablero() {
		board.reducirVampirosTablero();

	}
}

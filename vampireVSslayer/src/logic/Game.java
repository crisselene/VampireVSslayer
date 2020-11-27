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
	private GameObjectList obList;
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
		obList = new GameObjectList();
		player = new Player();
		ciclos = 0;
		board = new GameObjectBoard(level,obList);
		random = new Random(seed);
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
	/*public void actualizarPartida(GameObjectBoard  board, VampireList vampireList, Random random) {
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
	*/
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
		if(player.tieneMonedas()) {
			Slayer slayer = new Slayer(x, y);
			if(board.addSlayer(slayer, y, x)) {
				return true;
			}else {//Algun objeto en esa posición
				System.out.println("Esa fila esta ocupada");
				return false;
			}
			
		}else {//No tiene monedas
			System.out.println("Esa fila esta ocupada");
			return false;
		}		
	}

	//TODO:pasarlo a board
	/*public void addObject(GameObject objeto) {
		obList.anadirObjeto(objeto);
	}*/
		
	@Override
	public String getInfo() {
		String info = ("Monedas = " + player.getMonedas() + "\n" +
						"Ciclo : " + ciclos);
		return info;
	}

	public void doReset() {
		obList.resetList();
		
	}

	public void update() {
		ciclos ++;
		obList.attack();
		this.crearVampiro();
		obList.move();
		player.ganaMonedas(random.nextFloat());
	}

	private void crearVampiro() {
		int filaAleatoria = random.nextInt(level.getDimy());
		int columna = (level.getDimx() - 1);
		Vampiro v = new Vampiro(columna, filaAleatoria);
		board.addVampire(v,filaAleatoria,columna, random);
		/*boolean ocupado = obList.buscarObjeto(filaAleatoria, columna);
		if(!ocupado) {
			Vampiro v = new Vampiro(columna, filaAleatoria);
			boolean crear = board.frecuenciaLimiteVamps();
			if(crear) {
				this.addObject(v);System.out.println("se crea vamp");
			}else System.out.println("NO se crea Vamp");
		}*/
	}

}

package objetos;

import logic.Game;
import logic.GameObject;
import logic.IAttack;
import logic.Level;
 

public class Vampiro extends GameObject implements IAttack{

	public static Level level;
	protected int posx;
	protected int posy;
	public final static int DEFAULT_VIDA = 5;
	public final static int DEFAULT_CICLOS = 1;
	//VARIABLES ESTÁTICAS
	public static int vampiros_restantes; // vampiros que faltan por crear
	public static int vampiros_tablero = 0; //vampiros en el tablero
	
	protected int ciclosAvance; //variable que guarda la cantidad de ciclos hasta que el vampiro avance
	protected Game game;
	public static boolean llegoPrincipio = false;
	
	
	/**CONSTRUCTOR
	 * @param posx
	 * @param posy
	 * @param game 
	 * @param vida
	 * @param ciclosAvance
	 */
	
	public Vampiro(int posx,int posy, Game game) {
		this(posx, posy, DEFAULT_VIDA,DEFAULT_CICLOS, game);
	}
	
	public Vampiro(int posx, int posy, int vida, int ciclosAvance, Game game) {
		super(posx, posy, DEFAULT_VIDA, game);
		this.posx = posx;
		this.posy = posy;
		this.ciclosAvance = ciclosAvance;
		this.game = game;
		this.letra = "V ";
		Vampiro.vampiros_restantes--;
		Vampiro.vampiros_tablero++;
	}

	public int getCiclosAvance() {
		return ciclosAvance;
	}
	public void setCiclosAvance(int ciclosAvance) {
		this.ciclosAvance = ciclosAvance;
	}

	@Override
	public void move(boolean ocupado) {
		
		if(ciclosAvance>0) ciclosAvance--; 
		else {
			if(ocupado==false) {
				posx--;
				this.setPosX(posx); 
				this.setCiclosAvance(DEFAULT_CICLOS);
				if(posx==-1) Vampiro.llegoPrincipio=true;
			}
			else ciclosAvance--;				
		}
	}
		
	public boolean receiveSlayerAttack(int damage) {
		if(!isAlive()) {
			return false;
		}
		else {
			int vida= this.getVida();
			vida = vida - damage;
			this.setVida(vida);	
			if(!isAlive()) {
				this.morir();
			}
			return true;
		}		
	}
	
	public  boolean receiveGarlicPush() {
		//Si tiene alguien detras no retrocede y si esta al final muere
		if(game.getLevelDimX()-1 == posx) {
			this.morir();
			return true;
		}
		else if(!game.buscarObjeto(posx+1, posy)) {
			posx++;
			ciclosAvance = DEFAULT_CICLOS;
			this.setPosX(posx);
			return true;
		}
		else {
			ciclosAvance=DEFAULT_CICLOS;
			return true;	
		}
			
	}

	public boolean receiveLightFlash() {
		this.morir();
		return true;		
	}

	protected void morir() { 
		this.setVida(0);
		Vampiro.vampiros_tablero--;
	}

	@Override
	public void attack() {
			if (isAlive () ) {
				IAttack other = game.getAttackableInPosition(this.posx-1, this.posy);
				if (other != null ) {
					other.receiveVampireAttack(HARM);					
			} 	
		}
	}

	@Override
	public String serialize(String serialize) {
		return serialize + "V;" + posx + ";" + posy + ";" + getVida() + ";" + ciclosAvance;	
	}


	
}

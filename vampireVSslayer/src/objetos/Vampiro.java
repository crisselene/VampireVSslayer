package objetos;

import logic.Game;
import logic.GameObject;
import logic.IAttack;

public class Vampiro extends GameObject implements IAttack{

	protected int posx;
	protected int posy;
	public final static int DEFAULT_VIDA = 5;
	public final static int DEFAULT_CICLOS = 0;
	protected int ciclosAvance; //variable que guarda la cantidad de ciclos hasta que el vampiro avance
	protected Game game;
	public static boolean llegoFinal = false;
	
	
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
	}

	public int getCiclosAvance() {
		return ciclosAvance;
	}
	public void setCiclosAvance(int ciclosAvance) {
		this.ciclosAvance = ciclosAvance;
	}
	
	public static void setLlegoFinal(boolean llegoFinal) {
		Vampiro.llegoFinal = llegoFinal;
	}

	@Override
	public void move(boolean ocupado) {
		
		if(ciclosAvance<1) {
		ciclosAvance++; 
		}
		else {
			if(ocupado==false) {
				posx--;
				this.setPosX(posx); 
				ciclosAvance=0;
				if(posx==-1) setLlegoFinal(true);
			}
		}
	}
		
	public boolean receiveSlayerAttack(int damage) {
		int vida= this.getVida();
		vida = vida - damage;
		this.setVida(vida);	
		if(!isAlive()) {
			this.morir();
		}
		return true;
	}
	
	public  boolean receiveGarlicPush() {
		//Si tiene alguien detras no retrocede y si esta al final muere
		if(game.estaAlFinal(posx)) {
			this.morir();
			return true;
		}
		else if(!game.buscarObjeto(posx+1, posy)) {
			posx++;
			ciclosAvance = 0;
			this.setPosX(posx);
			return true;
		}
		else {
			ciclosAvance=0;
			return true;	
		}
			
	}
	
	

	public boolean receiveLightFlash() {
		//Fulmina a los vampiros
		this.morir();
		return true;		
	}

	protected void morir() { //vida a 0 y vampirosEnTablero--
		this.setVida(0);
		game.reducirVampirosTablero();
	}
	
//	@Override
//	public boolean contarVamp() {
//		return true;
//	}

	@Override
	public boolean noHayVenLafila(boolean crear, int fila) {
		 return this.posy == fila;
		
	}

	@Override
	public void attack() {
			if (isAlive () ) {
				IAttack other = game.getAttackableInPosition(this.posx-1, this.posy);
				if (other != null ) {
					boolean recibirDamage = other.receiveVampireAttack(HARM);
					if(recibirDamage== true) {
						if(this.ciclosAvance<2) {
							ciclosAvance++;
						}
					}
					
			} 	
		}
	}

//	@Override
//	public boolean llegoFinal() {
//		//if(posx == -1) {
//			//return true;
//		//}
//		//return false;
//		return posx==-1;
//	}

	
}

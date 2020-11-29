package objetos;

import logic.Game;
import logic.GameObject;
import logic.IAttack;

public class Vampiro extends GameObject implements IAttack{

	private int posx;
	private int posy;
	public final static int DEFAULT_VIDA = 5;
	public final static int DEFAULT_CICLOS = 0;
	//private int vida;
	private int ciclosAvance; //variable que guarda la cantidad de ciclos hasta que el vampiro avance
	private Game game;
	//el daño de los vampiros es 1
	
	
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
		//vienen de la clase Object
		this.posx = posx;
		this.posy = posy;
		//la vida de los vampiros siempre se incializa a 5 (se puede cambiar)
		//this.setVida(5);
		
		//????? ciclos avance??? ****************
		this.ciclosAvance = ciclosAvance;
		this.game = game;
	}

	//getters y setters de avanzar
	public int getCiclosAvance() {
		return ciclosAvance;
	}
	public void setCiclosAvance(int ciclosAvance) {
		this.ciclosAvance = ciclosAvance;
	}


	//Escribir el vampiro
	public String toString() {
		int vida = this.getVida();
		return "V [" + vida + "]";
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
			}//else ataque=false;
		}
	}
		
	public boolean receiveSlayerAttack(int damage) {
		int vida= this.getVida();
		vida = vida - damage;
		this.setVida(vida);		
		return true;
	}

	@Override
	public boolean contarVamp() {
		return true;
	}

	@Override
	public boolean noHayVenLafila(boolean crear, int fila) {
		if (this.posy==fila) return false;
		else return true;
		
	}

	@Override
	public void attack() {
		//ESTE NO, SIN PARÁMETROS
			if (isAlive () ) {
				IAttack other = game.getAttackableInPosition(this.posx-1, this.posy);
				if (other != null ) {
					other.receiveVampireAttack(HARM);
					//si los ciclos de espera del vampiro son menos de dos, se aumentan:
					if(this.ciclosAvance<2) {
						ciclosAvance++;
					}
			} 	
		}
	}

	@Override
	public boolean llegoFinal() {
		if(posx == -1) {
			return true;
		}
		return false;
	}

	
}

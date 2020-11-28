package objetos;

import logic.Game;
import logic.GameObject;
import logic.IAttack;
import logic.VampireList;

public class Vampiro extends GameObject implements IAttack{

	private int posx;
	private int posy;
	public final static int DEFAULT_VIDA = 5;
	public final static int DEFAULT_CICLOS = 0;
	public final static boolean DEFAULT_ATAQUE = false;
	//private int vida;
	private int ciclosAvance; //variable que guarda la cantidad de ciclos hasta que el vampiro avance
	private boolean ataque; //variable que guarda si le toca atacar a un vampiro
	private Game game;
	//el daño de los vampiros es 1
	int HARM = 1;
	
	
	/**CONSTRUCTOR
	 * @param posx
	 * @param posy
	 * @param game 
	 * @param vida
	 * @param ciclosAvance
	 * @param ataque
	 */
	
	public Vampiro(int posx,int posy, Game game) {
		this(posx, posy, DEFAULT_VIDA,DEFAULT_CICLOS,DEFAULT_ATAQUE, game);
	}
	
	public Vampiro(int posx, int posy, int vida, int ciclosAvance,boolean ataque, Game game) {
		super(posx, posy, DEFAULT_VIDA, game);
		//vienen de la clase Object
		this.posx = posx;
		this.posy = posy;
		//la vida de los vampiros siempre se incializa a 5 (se puede cambiar)
		//this.setVida(5);
		
		//????? ciclos avance??? ****************
		this.ciclosAvance = ciclosAvance;
		this.ataque = ataque;
		this.game = game;
	}


	//set para cambiar el vampiro a atacar(true) o no atacar (false)
	public void setAtaque(boolean ataque) {
		this.ataque = ataque;
	}

	//getter y setter pos y
	public int getPosy() {
		return posy;
	}
	public void setPosy(int posy) {
		this.posy = posy;
	}

	//getters y setters de avanzar
	public int getCiclosAvance() {
		return ciclosAvance;
	}
	public void setCiclosAvance(int ciclosAvance) {
		this.ciclosAvance = ciclosAvance;
	}

	//getter y setter de vida
	/*public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
*/

	//Escribir el vampiro
	public String toString() {
		int vida = this.getVida();
		return "V [" + vida + "]";
	}

	@Override
	public void move() {
		
		if(ciclosAvance!=2) {
		ciclosAvance++; 
		System.out.println("no me toca moverme");
		}
		else {
			if(ataque==false) {
				posx--;
				this.setPosX(posx); 
				ciclosAvance=0;
				System.out.println("me muevo");
			}else ataque=false;
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
					//el vampiro ha atacado
					this.ataque=true;
			} 	
		}
	}



	//TODO: generar posx y posy para vampiro
	/*@Override
	public void crearObject(int posx, int posy) {
		Vampiro v = new Vampiro(posy, posx, vida, ciclosAvance, ataque);
		list.anadirObjeto(v);
		}*/
	
}

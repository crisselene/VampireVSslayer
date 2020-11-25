package objetos;

import logic.Game;
import logic.GameObject;
import logic.GameObject.IAttack;
import logic.VampireList;

public class Vampiro extends GameObject {

	private int posx;
	private int posy;
	//private int vida;
	private int ciclosAvance; //variable que guarda la cantidad de ciclos hasta que el vampiro avance
	private boolean ataque; //variable que guarda si le toca atacar a un vampiro
	private Game game;
	
	
	
	/**CONSTRUCTOR
	 * @param posx
	 * @param posy
	 * @param vida
	 * @param ciclosAvance
	 * @param ataque
	 */
	public Vampiro(int posy, int posx, int vida, int ciclosAvance,boolean ataque) {
		super();
		//vienen de la clase Object
		this.posx = posx;
		this.posy = posy;
		//la vida de los vampiros siempre se incializa a 5 (se puede cambiar)
		this.vida = 5;
		
		//????? ciclos avance??? ****************
		this.ciclosAvance = ciclosAvance;
		this.ataque = ataque;
	}
	
	//getter y setter pos x
	public int getPosx() {
		return posx;
	}
	public void setPosx(int posx) {
		this.posx = posx;
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
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}


	//Escribir el vampiro
	public String toString() {
		return "V [" + vida + "]";
	}

	//Metodo que avanza al vampiro
	public static void avanza(VampireList vampireList) {
	//recorro el array de vampiros y compruebo si alguno tiene los ciclos a 2, 
		//entonces le tocará avanzar
		Vampiro[] arrayVamp = vampireList.getArrayVamp();
		for (int i = 0; i < arrayVamp.length; i++) {
			//compruebo que no ha atacado, porque si ataca, no avanza
				if(arrayVamp[i].ciclosAvance == 1 && arrayVamp[i].ataque == false) {//Si le toca avanzar...
					int posXv = arrayVamp[i].getPosx();
					posXv--; //avanza hacia los negativos en el eje x
					arrayVamp[i].setPosx(posXv);
					arrayVamp[i].setCiclosAvance(0); //sus ciclos se retardan 
							
				}
				//si no avanza, el ciclo del vampiro aumenta
				else {
					int cicloVamp = arrayVamp[i].getCiclosAvance();
					//si el ciclo es distinto a 2 (2 es el max)
					if(cicloVamp!=1)cicloVamp++;
					arrayVamp[i].setCiclosAvance(cicloVamp);
					//si el vampiro ha atacado (ataque a true) entonces le ponemos ataque a false
					arrayVamp[i].setAtaque(false);
					
				}
		}
		
	}
	
	public void attack() {
		if (isAlive () ) {
			//TODO: darle las coordenadas ddel que al que tiene que atacar
			IAttack other = game.getAttackableInPosition(x-1, y);
			if (other != null )
				other.receiveVampireAttack(HARM);
			} 
		}

	private boolean isAlive() {
		if(vida!=0) return true;
		else return false;
	}

	//TODO: generar posx y posy para vampiro
	@Override
	public void crearObject(int posx, int posy) {
		Vampiro v = new Vampiro(posy, posx, vida, ciclosAvance, ataque);
		list.anadirObjeto(v);
	}
}
